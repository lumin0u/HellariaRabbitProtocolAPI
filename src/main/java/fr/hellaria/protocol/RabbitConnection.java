package fr.hellaria.protocol;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;

import com.rabbitmq.client.AlreadyClosedException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import fr.hellaria.protocol.payloads.Payload;
import fr.hellaria.protocol.payloads.PayloadHandshakeSetProtocol;
import fr.hellaria.protocol.payloads.PayloadPing;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerNicked;
import fr.hellaria.protocol.payloads.PayloadPong;
import fr.hellaria.protocol.payloads.PayloadServerInfo;
import fr.hellaria.protocol.payloads.PayloadServerTypeAndGame;

public class RabbitConnection
{
	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private PayloadHandler handler;
	private String serverName;
	private Map<Integer, Predicate<Payload>> predicates;
	private int predicatesId;
	
	// sendPacket(new PayloadHandshakeSetProtocol(), "proxy");
	// sendPacket(new PayloadServerInfo(server), "proxy");
	
	public RabbitConnection(String serverName, PayloadHandler handler) throws IOException, TimeoutException
	{
		factory = new ConnectionFactory();
		connection = factory.newConnection();
		channel = connection.createChannel();
		this.serverName = serverName;
		this.handler = handler;
		this.predicates = new HashMap<>();
		
		channel.queueDeclare(serverName, false, false, false, null);
		System.out.println("[ProtocolAPI] Deleted "+channel.queuePurge(serverName).getMessageCount()+" messages in queue.");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) ->
		{
			PayloadDeserializer deserializer = new PayloadDeserializer(delivery.getBody());
			
			int packetId = deserializer.readVarInt();
			
			if(packetId == 0x7e)
			{
				if(handler != null)
					handler.handlePayloadStop();
				return;
			}
			
			String source = deserializer.readString();
			
			Payload payload = Payload.payloadFrom(packetId);
			payload.deserialize(deserializer);
			
			for(Entry<Integer, Predicate<Payload>> entry : predicates.entrySet())
				if(entry.getValue().test(payload))
					predicates.remove(entry.getKey());
			
			if(payload instanceof PayloadHandshakeSetProtocol)
			{
				if(((PayloadHandshakeSetProtocol)payload).getVersion() != HellariaProtocol.PROTOCOL_VERSION)
				{
					if(((PayloadHandshakeSetProtocol)payload).getVersion() > HellariaProtocol.PROTOCOL_VERSION)
					{
						System.err.println("[ProtocolAPI] Ce serveur ne possède pas la derniere version du protocol hellaria, la communication est arrétée.");
						try
						{
							close();
						}catch(TimeoutException e)
						{
							e.printStackTrace();
						}
					}
					else
					{
						System.err.println("[ProtocolAPI] Un serveur distant ne possède pas la derniere version du protocol hellaria.");
						sendPacket(new PayloadHandshakeSetProtocol(), source);
					}
				}
			}
			else if(payload instanceof PayloadPing)
			{
				sendPacket(new PayloadPong(), source);
			}
			
			else if(this.handler != null)
			{
				if(payload instanceof PayloadServerInfo)
					this.handler.handleServerInfo((PayloadServerInfo)payload, source);
				
				if(payload instanceof PayloadPlayerInfo)
					this.handler.handlePlayerInfo((PayloadPlayerInfo)payload, source);
				
				if(payload instanceof PayloadPlayerNicked)
					this.handler.handlePlayerNicked((PayloadPlayerNicked)payload, source);
				
				if(payload instanceof PayloadServerTypeAndGame)
					this.handler.handleServerTypeAndGame((PayloadServerTypeAndGame)payload, source);
			}
		};
		
		channel.basicConsume(serverName, true, deliverCallback, consumerTag ->
		{});
	}
	
	public void sendPacket(Payload payload, HellariaServer target) throws IOException, AlreadyClosedException
	{
		sendPacket(payload, target.getName());
	}
	
	public void sendPacket(Payload payload, String target) throws IOException, AlreadyClosedException
	{
		PayloadSerializer serializer = new PayloadSerializer();
		serializer.writeInt(Payload.idFrom(payload));
		serializer.writeString(serverName);
		payload.serialize(serializer);
		
		channel.queueDeclare(target, false, false, false, null);
		channel.basicPublish("", target, null, serializer.getBytes());
	}
	
	public void setHandler(PayloadHandler handler)
	{
		this.handler = handler;
	}
	
	public boolean isClosed()
	{
		return !connection.isOpen() || !channel.isOpen();
	}
	
	public void close() throws IOException, TimeoutException, AlreadyClosedException
	{
		connection.close();
	}
	
	public boolean waitFor(Predicate<Payload> predicate, long timeOut) throws InterruptedException
	{
		long startDate = System.currentTimeMillis();
		
		int id = predicatesId++;
		predicates.put(id, predicate);
		
		while(true)
		{
			Thread.sleep(1);
			
			if(System.currentTimeMillis() - timeOut > startDate)
			{
				predicates.remove(id);
				return false;
			}
			if(!predicates.containsKey(id))
			{
				return true;
			}
		}
	}
}

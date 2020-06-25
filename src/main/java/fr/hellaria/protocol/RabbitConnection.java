package fr.hellaria.protocol;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import fr.hellaria.protocol.payloads.PayloadAskServers;
import fr.hellaria.protocol.payloads.PayloadFriends;
import fr.hellaria.protocol.payloads.PayloadHandshake;
import fr.hellaria.protocol.payloads.PayloadMonarias;
import fr.hellaria.protocol.payloads.PayloadOnlineCount;
import fr.hellaria.protocol.payloads.PayloadParty;
import fr.hellaria.protocol.payloads.PayloadPing;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerNicked;
import fr.hellaria.protocol.payloads.PayloadPong;
import fr.hellaria.protocol.payloads.PayloadRestart;
import fr.hellaria.protocol.payloads.PayloadSendToServer;
import fr.hellaria.protocol.payloads.PayloadServerInfo;
import fr.hellaria.protocol.payloads.PayloadServerTypeAndName;

public class RabbitConnection
{
	private ConnectionFactory factory;
	private Connection connection;
	private Channel channel;
	private List<PayloadHandler> handlers;
	private String serverName;
	private Map<Integer, Predicate<Payload>> predicates;
	private int predicatesId;
	
	public RabbitConnection(String serverName) throws IOException, TimeoutException
	{
		if(serverName == null || serverName.equals(""))
			throw new IllegalArgumentException("serverName cannot be null/empty");
		
		System.out.println("[ProtocolAPI] Initialisation da la connection rabbitmq (protocol version : " + HellariaProtocol.PROTOCOL_VERSION + ").");
		factory = new ConnectionFactory();
		connection = factory.newConnection();
		channel = connection.createChannel();
		this.serverName = serverName;
		this.handlers = new ArrayList<>();
		this.predicates = new HashMap<>();
		
		channel.queueDeclare(serverName, false, false, false, null);
		System.out.println("[ProtocolAPI] " + channel.queuePurge(serverName).getMessageCount() + " messages ont ete supprimes de la queue.");
		
		DeliverCallback deliverCallback = (consumerTag, delivery) ->
		{
			try
			{
				PayloadDeserializer deserializer = new PayloadDeserializer(delivery.getBody());

				int packetId = deserializer.readVarInt();
				
				if(packetId == 0x7e)
				{
					for(PayloadHandler handler : handlers)
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
				
				if(payload instanceof PayloadHandshake)
				{
					for(PayloadHandler handler : handlers)
						if(handler != null)
							handler.handleHandshake((PayloadHandshake)payload, source);
						
					if(((PayloadHandshake)payload).getVersion() != HellariaProtocol.PROTOCOL_VERSION)
					{
						if(((PayloadHandshake)payload).getVersion() > HellariaProtocol.PROTOCOL_VERSION)
						{
							System.err.println("[ProtocolAPI] Ce serveur ne possède pas la derniere version du protocol hellaria.");
						}
						else
						{
							System.err.println("[ProtocolAPI] \"" + source + "\" ne possède pas la derniere version du protocol hellaria.");
						}
					}
				}
				else if(payload instanceof PayloadPing)
				{
					sendPacket(new PayloadPong(), source);
				}
				
				else
				{
					for(PayloadHandler handler : handlers)
					{
						if(handler != null)
						{
							if(payload instanceof PayloadServerInfo)
								handler.handleServerInfo((PayloadServerInfo)payload, source);
							
							if(payload instanceof PayloadPlayerInfo)
								handler.handlePlayerInfo((PayloadPlayerInfo)payload, source);
							
							if(payload instanceof PayloadPlayerNicked)
								handler.handlePlayerNicked((PayloadPlayerNicked)payload, source);
							
							if(payload instanceof PayloadServerTypeAndName)
								handler.handleServerTypeAndGame((PayloadServerTypeAndName)payload, source);
							
							if(payload instanceof PayloadParty)
								handler.handleParty((PayloadParty)payload, source);
							
							if(payload instanceof PayloadMonarias)
								handler.handleMonarias((PayloadMonarias)payload, source);
							
							if(payload instanceof PayloadSendToServer)
								handler.handleSendToServer((PayloadSendToServer)payload, source);
							
							if(payload instanceof PayloadRestart)
								handler.handleRestart((PayloadRestart)payload, source);
							
							if(payload instanceof PayloadFriends)
								handler.handleFriends((PayloadFriends)payload, source);
							
							if(payload instanceof PayloadOnlineCount)
								handler.handlePlayerCount((PayloadOnlineCount)payload, source);
							
							if(payload instanceof PayloadAskServers)
								handler.handleAskServers((PayloadAskServers)payload, source);
						}
					}
				}
			}catch(Exception e)
			{
				e.printStackTrace();
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
		if(target == null || target.equals(""))
			throw new IllegalArgumentException("target cannot be null/empty");
		if(payload == null)
			throw new IllegalArgumentException("payload cannot be null");
		
		PayloadSerializer serializer = new PayloadSerializer();
		serializer.writeVarInt(Payload.idFrom(payload.getClass()));
		serializer.writeString(serverName);
		payload.serialize(serializer);
		
		channel.basicPublish("", target, null, serializer.getBytes());
	}
	
	public void addHandler(PayloadHandler handler)
	{
		this.handlers.add(handler);
	}
	
	public void rmHandler(PayloadHandler handler)
	{
		if(handler != null)
			this.handlers.remove(handler);
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

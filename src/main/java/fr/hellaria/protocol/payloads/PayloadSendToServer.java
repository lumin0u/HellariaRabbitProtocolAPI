package fr.hellaria.protocol.payloads;

import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadSendToServer extends Payload
{
	List<String> uuids;
	String server;
	
	public PayloadSendToServer()
	{}
	
	public PayloadSendToServer(List<String> uuids, String server)
	{
		this.uuids = uuids;
		this.server = server;
	}

	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeVarInt(uuids.size());
		uuids.forEach(uuid -> serializer.writeString(uuid));
		serializer.writeString(server);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		uuids = new ArrayList<>();
		for(int i = 0; i < deserializer.readVarInt(); i++)
			uuids.add(deserializer.readString());
		server = deserializer.readString();
	}
	
	public List<String> getUuids()
	{
		return uuids;
	}
	
	public void setUuids(List<String> uuids)
	{
		this.uuids = uuids;
	}
	
	public String getServer()
	{
		return server;
	}
	
	public void setServer(String server)
	{
		this.server = server;
	}
}

package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.HellariaProtocol;
import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadHandshake extends Payload
{
	private int version;
//	private boolean hub;
	
	public PayloadHandshake()
	{
		
	}
	
//	public PayloadHandshake(boolean hub)
//	{
//		this.hub = hub;
//	}
	
	public int getVersion()
	{
		return version;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeVarInt(HellariaProtocol.PROTOCOL_VERSION);
//		serializer.writeBoolean(hub);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		version = deserializer.readVarInt();
//		hub = deserializer.readBoolean();
	}

//	public boolean isHub()
//	{
//		return hub;
//	}
//
//	public void setHub(boolean hub)
//	{
//		this.hub = hub;
//	}
}

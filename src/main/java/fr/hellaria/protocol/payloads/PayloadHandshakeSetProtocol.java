package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.HellariaProtocol;
import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadHandshakeSetProtocol extends Payload
{
	private int version;
	
	public PayloadHandshakeSetProtocol()
	{
		
	}
	
	public int getVersion()
	{
		return version;
	}

	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeVarInt(HellariaProtocol.PROTOCOL_VERSION);
	}

	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		version = deserializer.readVarInt();
	}
}

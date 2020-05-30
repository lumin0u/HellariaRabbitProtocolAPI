package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

@SuppressWarnings("unchecked")
public abstract class Payload
{
	public abstract void serialize(PayloadSerializer serializer);
	
	public abstract void deserialize(PayloadDeserializer deserializer);
	
	private static Class<? extends Payload>[] payloads;
	
	static
	{
		payloads = new Class[6];
		payloads[0] = PayloadHandshakeSetProtocol.class;
		payloads[1] = PayloadPing.class;
		payloads[2] = PayloadPong.class;
		payloads[3] = PayloadServerInfo.class;
		payloads[4] = PayloadPlayerInfo.class;
		payloads[5] = PayloadPlayerNicked.class;
	}
	
	public static Payload payloadFrom(int id)
	{
		try
		{
			return payloads[id].newInstance();
		}catch(InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static int idFrom(Payload payload)
	{
		for(int i = 0; i < payloads.length; i++)
			if(payloads[i].equals(payload.getClass()))
				return i;
		return -1;
	}
	
//	public static int idFrom(Class<? extends Payload> payload)
//	{
//		for(int i = 0; i < payloads.length; i++)
//			if(payloads[i].equals(payload))
//				return i;
//		return -1;
//	}
}
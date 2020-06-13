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
		payloads = new Class[15];
		payloads[0] = PayloadHandshake.class;
		payloads[1] = PayloadPing.class;
		payloads[2] = PayloadPong.class;
		payloads[3] = PayloadServerInfo.class;
		payloads[4] = PayloadPlayerInfo.class;
		payloads[5] = PayloadPlayerNicked.class;
		payloads[6] = PayloadServerTypeAndGame.class;
		payloads[7] = PayloadMonarias.class;
		payloads[8] = PayloadParty.class;
		payloads[9] = PayloadRestart.class;
		payloads[10] = PayloadFriends.class;
		payloads[11] = PayloadPlayerPosition.class;
		payloads[12] = PayloadPlayerAskPosition.class;
		payloads[13] = PayloadAskServers.class;
		payloads[14] = PayloadOnlineCount.class;
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
	
	public static int idFrom(Class<? extends Payload> payload)
	{
		for(int i = 0; i < payloads.length; i++)
			if(payloads[i].equals(payload))
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

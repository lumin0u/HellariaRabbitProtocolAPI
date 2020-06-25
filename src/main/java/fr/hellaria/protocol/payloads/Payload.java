package fr.hellaria.protocol.payloads;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public abstract class Payload
{
	public abstract void serialize(PayloadSerializer serializer);
	
	public abstract void deserialize(PayloadDeserializer deserializer) throws EOFException;
	
	private static List<Class<? extends Payload>> payloads = new ArrayList<>();
	
	static
	{
		payloads.add(PayloadHandshake.class);
		payloads.add(PayloadPing.class);
		payloads.add(PayloadPong.class);
		payloads.add(PayloadServerInfo.class);
		payloads.add(PayloadPlayerInfo.class);
		payloads.add(PayloadPlayerNicked.class);
		payloads.add(PayloadServerTypeAndName.class);
		payloads.add(PayloadMonarias.class);
		payloads.add(PayloadParty.class);
		payloads.add(PayloadRestart.class);
		payloads.add(PayloadFriends.class);
		payloads.add(PayloadAskServers.class);
		payloads.add(PayloadOnlineCount.class);
		payloads.add(PayloadSendToServer.class);
	}
	
	public static Payload payloadFrom(int id)
	{
		return payloads.stream().filter(p -> idFrom(p) == id).findFirst().map(p -> {
			try
			{
				return p.newInstance();
			}catch(InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
				return null;
			}
		}).get();
	}
	
	public static int idFrom(Class<? extends Payload> payload)
	{
		try
		{
			return payload.getDeclaredField("id").getInt(null);
		}catch(IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e)
		{
			e.printStackTrace();
		}
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

package fr.hellaria.protocol.payloads;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadFriends extends Payload
{
	public static final int id = 10;
	
	String uuid;
	List<String> friends;
	
	public PayloadFriends()
	{
		
	}
	
	public PayloadFriends(String uuid, List<String> friends)
	{
		this.uuid = uuid;
		this.friends = friends;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(uuid);
		serializer.writeVarInt(friends.size());
		friends.forEach(friend -> serializer.writeString(friend));
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer) throws EOFException
	{
		uuid = deserializer.readString();
		friends = new ArrayList<>();
		int len = deserializer.readVarInt();
		for(int i = 0; i < len; i++)
			friends.add(deserializer.readString());
	}
	
	public String getUuid()
	{
		return uuid;
	}
	
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
	
	public List<String> getFriends()
	{
		return friends;
	}
	
	public void setFriends(List<String> friends)
	{
		this.friends = friends;
	}
}

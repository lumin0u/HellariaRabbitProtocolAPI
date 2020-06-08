package fr.hellaria.protocol.payloads;

import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadFriends extends Payload
{
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
	public void deserialize(PayloadDeserializer deserializer)
	{
		uuid = deserializer.readString();
		friends = new ArrayList<>();
		for(int i = 0; i < deserializer.readVarInt(); i++)
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

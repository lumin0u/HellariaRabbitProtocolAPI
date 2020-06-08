package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadPlayerAskPosition extends Payload
{
	String player;
	
	public PayloadPlayerAskPosition()
	{
		
	}
	
	public PayloadPlayerAskPosition(String player)
	{
		this.player = player;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(player);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		player = deserializer.readString();
	}
	
	public String getPlayer()
	{
		return player;
	}
	
	public void setPlayer(String player)
	{
		this.player = player;
	}
}

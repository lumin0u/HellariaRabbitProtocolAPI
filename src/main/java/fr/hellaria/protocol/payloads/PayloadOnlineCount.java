package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadOnlineCount extends Payload
{
	int playerCount;
	
	public PayloadOnlineCount()
	{
		
	}

	public PayloadOnlineCount(int playerCount)
	{
		this.playerCount = playerCount;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeVarInt(playerCount);
	}

	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		playerCount = deserializer.readVarInt();
	}

	public int getPlayerCount()
	{
		return playerCount;
	}

	public void setPlayerCount(int playerCount)
	{
		this.playerCount = playerCount;
	}
}

package fr.hellaria.protocol.payloads;

import java.io.EOFException;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadOnlineCount extends Payload
{
	public static final int id = 12;
	
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
	public void deserialize(PayloadDeserializer deserializer) throws EOFException
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

package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadServerTypeAndGame extends Payload
{
	String type;
	String game;
	
	public PayloadServerTypeAndGame()
	{
		
	}
	
	public PayloadServerTypeAndGame(String type, String game)
	{
		this.type = type;
		this.game = game;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String getGame()
	{
		return game;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(type);
		serializer.writeString(game);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		type = deserializer.readString();
		game = deserializer.readString();
	}
}

package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadServerTypeAndGame extends Payload
{
	String server;
	String type;
	String game;
	
	public PayloadServerTypeAndGame()
	{
		
	}
	
	public PayloadServerTypeAndGame(String type, String game, String server)
	{
		this.type = type;
		this.game = game;
		this.server = server;
	}
	
	public String getServer()
	{
		return server;
	}
	
	public void setServer(String server)
	{
		this.server = server;
	}
	
	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getGame()
	{
		return game;
	}
	
	public void setGame(String game)
	{
		this.game = game;
	}

	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(type);
		serializer.writeString(game);
		serializer.writeString(server);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		type = deserializer.readString();
		game = deserializer.readString();
		server = deserializer.readString();
	}
}

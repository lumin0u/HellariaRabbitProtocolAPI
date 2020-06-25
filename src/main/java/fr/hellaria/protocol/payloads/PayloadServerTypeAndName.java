package fr.hellaria.protocol.payloads;

import java.io.EOFException;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadServerTypeAndName extends Payload
{
	public static final int id = 6;
	
	String server;
	String type;
	String name;
	String category;
	
	public PayloadServerTypeAndName()
	{
		
	}
	
	public PayloadServerTypeAndName(String type, String name, String server, String category)
	{
		this.type = type;
		this.name = name;
		this.server = server;
		this.category = category;
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
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String game)
	{
		this.name = game;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(type);
		serializer.writeString(name);
		serializer.writeString(server);
		serializer.writeString(category);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer) throws EOFException
	{
		type = deserializer.readString();
		name = deserializer.readString();
		server = deserializer.readString();
		category = deserializer.readString();
	}
}

package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadPlayerPosition extends Payload
{
	String player;
	double x;
	double y;
	double z;
	
	public PayloadPlayerPosition()
	{
		
	}
	
	public PayloadPlayerPosition(String player, double x, double y, double z)
	{
		this.player = player;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(player);
		serializer.writeDouble(x);
		serializer.writeDouble(y);
		serializer.writeDouble(z);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		player = deserializer.readString();
		x = deserializer.readDouble();
		y = deserializer.readDouble();
		z = deserializer.readDouble();
	}
	
	public String getPlayer()
	{
		return player;
	}
	
	public void setPlayer(String player)
	{
		this.player = player;
	}
	
	public double getX()
	{
		return x;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
	
	public double getZ()
	{
		return z;
	}
	
	public void setZ(double z)
	{
		this.z = z;
	}
}

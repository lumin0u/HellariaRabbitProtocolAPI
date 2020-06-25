package fr.hellaria.protocol.payloads;

import java.io.EOFException;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadMonarias extends Payload
{
	public static final int id = 7;
	
	EnumMonariasAction action;
	double amount;
	String player;
	
	public PayloadMonarias()
	{
		
	}
	
	public PayloadMonarias(EnumMonariasAction action, double amount, String player)
	{
		this.action = action;
		this.amount = amount;
		this.player = player;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(player);
		serializer.writeVarInt(action.ordinal());
		serializer.writeDouble(amount);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer) throws EOFException
	{
		player = deserializer.readString();
		action = EnumMonariasAction.values()[deserializer.readVarInt()];
		amount = deserializer.readDouble();
	}
	
	public EnumMonariasAction getAction()
	{
		return action;
	}
	
	public void setAction(EnumMonariasAction action)
	{
		this.action = action;
	}
	
	public double getAmount()
	{
		return amount;
	}
	
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	
	public String getPlayer()
	{
		return player;
	}
	
	public void setPlayer(String player)
	{
		this.player = player;
	}
	
	public static enum EnumMonariasAction
	{
		RESET,
		ADD,
		REMOVE;
	}
}

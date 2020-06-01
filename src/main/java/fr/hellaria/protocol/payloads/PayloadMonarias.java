package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadMonarias extends Payload
{
	EnumMonariasAction action;
	double amount;
	
	public PayloadMonarias()
	{
		
	}
	
	public PayloadMonarias(EnumMonariasAction action, double amount)
	{
		this.action = action;
		this.amount = amount;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeVarInt(action.ordinal());
		serializer.writeDouble(amount);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		action = EnumMonariasAction.values()[deserializer.readVarInt()];
		amount = deserializer.readDouble();
	}
	
	public static enum EnumMonariasAction
	{
		RESET,
		ADD,
		REMOVE;
	}
}

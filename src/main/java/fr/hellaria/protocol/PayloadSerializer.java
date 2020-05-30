package fr.hellaria.protocol;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class PayloadSerializer
{
	private byte[] payload;
	
	public PayloadSerializer()
	{
		this.payload = new byte[0];
	}
	
	public void writeVarInt(int value)
	{
		do
		{
			byte temp = (byte)(value & 0b01111111);
			value >>>= 7;
			if(value != 0)
				temp |= 0b10000000;
			writeByte(temp);
		}while(value != 0);
	}
	
	public void writeVarLong(long value)
	{
		do
		{
			byte temp = (byte)(value & 0b01111111);
			value >>>= 7;
			if(value != 0)
				temp |= 0b10000000;
			writeByte(temp);
		}while(value != 0);
	}
	
	public void writeString(String value)
	{
		writeVarInt(value.length());
		writeBytes(value.getBytes());
	}
	
	public void writeShort(short value)
	{
		writeBytes(ByteBuffer.allocate(2).putShort(value).array());
	}
	
	public void writeInt(int value)
	{
		writeBytes(ByteBuffer.allocate(4).putInt(value).array());
	}
	
	public void writeLong(long value)
	{
		writeBytes(ByteBuffer.allocate(8).putLong(value).array());
	}
	
	public void writeBoolean(boolean value)
	{
		writeByte(value ? (byte)1 : (byte)0);
	}
	
	public void writeByte(byte value)
	{
		payload = Arrays.copyOf(payload, payload.length + 1);
		payload[payload.length - 1] = value;
	}
	
	private void writeBytes(byte[] value)
	{
		for(byte b : value)
			writeByte(b);
	}
	
	public byte[] getBytes()
	{
		return payload.clone();
	}
}

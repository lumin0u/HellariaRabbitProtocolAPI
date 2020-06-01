package fr.hellaria.protocol;

import java.nio.ByteBuffer;

public class PayloadDeserializer
{
	private byte[] payload;
	private int index;
	
	public PayloadDeserializer(byte[] payload)
	{
		this.payload = payload;
	}
	
	public byte readByte()
	{
		return payload[index++];
	}
	
	public byte[] readBytes(int len)
	{
		byte[] bytes = new byte[len];
		for(int i = 0; i < len; i++)
			bytes[i] = readByte();
		return bytes;
	}
	
	public byte[] readFullBytes()
	{
		byte[] bytes = new byte[payload.length-index];
		for(int i = 0; i < bytes.length; i++)
			bytes[i] = readByte();
		return bytes;
	}
	
	public boolean readBoolean()
	{
		return readByte() != 0;
	}
	
	public double readDouble()
	{
		ByteBuffer buffer = ByteBuffer.allocate(8);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getDouble(0);
	}
	
	public float readFloat()
	{
		ByteBuffer buffer = ByteBuffer.allocate(4);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getFloat(0);
	}
	
	public int readInt()
	{
		ByteBuffer buffer = ByteBuffer.allocate(4);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getInt(0);
	}
	
	public long readLong()
	{
		ByteBuffer buffer = ByteBuffer.allocate(8);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getLong(0);
	}
	
	public short readShort()
	{
		ByteBuffer buffer = ByteBuffer.allocate(2);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getShort(0);
	}
	
	public String readString()
	{
		int len = readVarInt();
		byte[] bytes = new byte[len];
		for(int i = 0; i < len; i++)
			bytes[i] = readByte();
		return new String(bytes);
	}
	
	public int readVarInt()
	{
		int numRead = 0;
		int result = 0;
		byte read;
		do
		{
			read = readByte();
			int value = (read & 0b01111111);
			result |= (value << (7 * numRead));
			
			numRead++;
			if(numRead > 5)
			{
				throw new RuntimeException("VarInt is too big");
			}
		}while((read & 0b10000000) != 0);
		
		return result;
	}
	
	public long readVarLong()
	{
		int numRead = 0;
		long result = 0;
		byte read;
		do
		{
			read = readByte();
			int value = (read & 0b01111111);
			result |= (value << (7 * numRead));
			
			numRead++;
			if(numRead > 10)
			{
				throw new RuntimeException("VarLong is too big");
			}
		}while((read & 0b10000000) != 0);
		
		return result;
	}
}

package fr.hellaria.protocol;

import java.io.EOFException;
import java.nio.ByteBuffer;

public class PayloadDeserializer
{
	private byte[] payload;
	private int index;
	
	public PayloadDeserializer(byte[] payload)
	{
		this.payload = payload;
	}
	
	public byte readByte() throws EOFException
	{
		if(index >= payload.length)
			throw new EOFException();
		return payload[index++];
	}
	
	public byte[] readBytes(int len) throws EOFException
	{
		byte[] bytes = new byte[len];
		for(int i = 0; i < len; i++)
			bytes[i] = readByte();
		return bytes;
	}
	
	public byte[] remainingBytes()
	{
		byte[] bytes = new byte[payload.length-index];
		for(int i = 0; i < bytes.length; i++)
			bytes[i] = payload[i+index];
		return bytes;
	}
	
	public int remaining()
	{
		return payload.length-index;
	}
	
	public boolean readBoolean() throws EOFException
	{
		return readByte() != 0;
	}
	
	public double readDouble() throws EOFException
	{
		ByteBuffer buffer = ByteBuffer.allocate(8);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getDouble(0);
	}
	
	public float readFloat() throws EOFException
	{
		ByteBuffer buffer = ByteBuffer.allocate(4);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getFloat(0);
	}
	
	public int readInt() throws EOFException
	{
		ByteBuffer buffer = ByteBuffer.allocate(4);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getInt(0);
	}
	
	public long readLong() throws EOFException
	{
		ByteBuffer buffer = ByteBuffer.allocate(8);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getLong(0);
	}
	
	public short readShort() throws EOFException
	{
		ByteBuffer buffer = ByteBuffer.allocate(2);
		for(int i = 0; i < buffer.capacity(); i++)
			buffer.put(readByte());
		return buffer.getShort(0);
	}
	
	public String readString() throws EOFException
	{
		int len = readVarInt();
		return new String(readBytes(len));
	}
	
	public int readVarInt() throws EOFException
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
	
	public long readVarLong() throws EOFException
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

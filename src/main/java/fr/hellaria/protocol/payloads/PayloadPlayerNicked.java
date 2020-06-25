package fr.hellaria.protocol.payloads;

import java.io.EOFException;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadPlayerNicked extends Payload
{
	public static final int id = 5;
	
	private String uid;
	private String nickName;
	private String skinSignature;
	private String skinValue;
	private PayloadPlayerInfo.EnumRankPlayer displayRank;
	
	public PayloadPlayerNicked()
	{}
	
	public PayloadPlayerNicked(String uid, String nickName, String skinSignature, String skinValue, PayloadPlayerInfo.EnumRankPlayer displayRank)
	{
		this.uid = uid;
		this.nickName = nickName;
		this.displayRank = displayRank;
		this.skinValue = skinValue;
		this.skinSignature = skinSignature;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(this.uid);
		serializer.writeString(this.nickName);
		serializer.writeString(this.skinSignature);
		serializer.writeString(this.skinValue);
		serializer.writeVarInt(this.displayRank.ordinal());
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer) throws EOFException
	{
		this.uid = deserializer.readString();
		this.nickName = deserializer.readString();
		this.skinSignature = deserializer.readString();
		this.skinValue = deserializer.readString();
		this.displayRank = PayloadPlayerInfo.EnumRankPlayer.values()[deserializer.readVarInt()];
	}

	public String getUid()
	{
		return uid;
	}

	public void setUid(String uid)
	{
		this.uid = uid;
	}

	public String getNickName()
	{
		return nickName;
	}

	public void setNickName(String nickName)
	{
		this.nickName = nickName;
	}

	public String getSkinSignature()
	{
		return skinSignature;
	}

	public void setSkinSignature(String skinSignature)
	{
		this.skinSignature = skinSignature;
	}

	public String getSkinValue()
	{
		return skinValue;
	}

	public void setSkinValue(String skinValue)
	{
		this.skinValue = skinValue;
	}

	public PayloadPlayerInfo.EnumRankPlayer getDisplayRank()
	{
		return displayRank;
	}

	public void setDisplayRank(PayloadPlayerInfo.EnumRankPlayer displayRank)
	{
		this.displayRank = displayRank;
	}
}

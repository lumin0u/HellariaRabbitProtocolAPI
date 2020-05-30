package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadPlayerNicked extends Payload
{
	private String uid;
	private String nickName;
	private int skinId;
	private PayloadPlayerInfo.EnumRankPlayer displayRank;
	
	public PayloadPlayerNicked()
	{}
	
	public PayloadPlayerNicked(String uid, String nickName, int skinId, PayloadPlayerInfo.EnumRankPlayer displayRank)
	{
		this.uid = uid;
		this.nickName = nickName;
		this.skinId = skinId;
		this.displayRank = displayRank;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(this.uid);
		serializer.writeString(this.nickName);
		serializer.writeVarInt(this.skinId);
		serializer.writeVarInt(this.displayRank.ordinal());
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		this.uid = deserializer.readString();
		this.nickName = deserializer.readString();
		this.skinId = deserializer.readVarInt();
		this.displayRank = PayloadPlayerInfo.EnumRankPlayer.values()[deserializer.readVarInt()];
	}
}

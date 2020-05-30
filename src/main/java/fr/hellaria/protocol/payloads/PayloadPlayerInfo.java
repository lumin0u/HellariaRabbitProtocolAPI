package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadPlayerInfo extends Payload
{
	private String uid;
	private boolean spectator;
	private boolean nicked;
	private EnumRankStaff rankStaff;
	private EnumRankPlayer rankPlayer;
	private int partyId;
	private String spectatingTarget;
	
	public PayloadPlayerInfo()
	{}
	
	public PayloadPlayerInfo(String uid, boolean spectator, boolean nicked, EnumRankStaff rankStaff, EnumRankPlayer rankPlayer, int partyId, String spectatingTarget)
	{
		this.uid = uid;
		this.spectator = spectator;
		this.nicked = nicked;
		this.rankStaff = rankStaff;
		this.rankPlayer = rankPlayer;
		this.partyId = partyId;
		this.spectatingTarget = spectatingTarget;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(this.uid);
		serializer.writeVarInt(this.rankPlayer.ordinal());
		serializer.writeVarInt(this.rankStaff.ordinal());
		serializer.writeVarInt(this.partyId);
		serializer.writeBoolean(this.nicked);
		serializer.writeBoolean(this.spectator);
		if(this.spectator)
		{
			serializer.writeString(this.spectatingTarget);
		}
	}
	
	public String getUid()
	{
		return this.uid;
	}
	
	public boolean isSpectator()
	{
		return this.spectator;
	}
	
	public boolean isNicked()
	{
		return this.nicked;
	}
	
	public EnumRankStaff getRankStaff()
	{
		return this.rankStaff;
	}
	
	public EnumRankPlayer getRankPlayer()
	{
		return this.rankPlayer;
	}
	
	public int getPartyId()
	{
		return this.partyId;
	}
	
	public String getSpectatingTarget()
	{
		return this.spectatingTarget;
	}

	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		this.uid = deserializer.readString();
		this.rankPlayer = EnumRankPlayer.values()[deserializer.readVarInt()];
		this.rankStaff = EnumRankStaff.values()[deserializer.readVarInt()];
		this.partyId = deserializer.readVarInt();
		this.nicked = deserializer.readBoolean();
		this.spectator = deserializer.readBoolean();
		this.spectatingTarget = "";
		if(this.spectator)
			this.spectatingTarget = deserializer.readString();
	}
	
	public enum EnumRankStaff
	{
		NO_STAFF,
		DEV,
		AMI,
		STAFF,
		ADMIN,
		MOD,
		HELPER,
		BUILD,
		RESP;
	}
	
	public enum EnumRankPlayer
	{
		PLAYER,
		VIP,
		ELITARIA;
	}
}

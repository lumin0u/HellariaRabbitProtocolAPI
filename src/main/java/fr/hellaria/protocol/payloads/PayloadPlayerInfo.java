package fr.hellaria.protocol.payloads;

import java.util.HashMap;
import java.util.Map.Entry;

import fr.hellaria.protocol.HellariaPlayer;
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
	private HashMap<String, Object> settings;
	
	public PayloadPlayerInfo()
	{}
	
	public PayloadPlayerInfo(HellariaPlayer player)
	{
		this.uid = player.getUid();
		this.spectator = player.isSpectator();
		this.nicked = player.isNicked();
		this.rankStaff = player.getRankStaff();
		this.rankPlayer = player.getRankPlayer();
		this.partyId = player.getPartyId();
		this.spectatingTarget = player.getSpectatingTarget();
		this.settings = player.getSettings();
	}
	
	public PayloadPlayerInfo(String uid, boolean spectator, boolean nicked, EnumRankStaff rankStaff, EnumRankPlayer rankPlayer, int partyId, String spectatingTarget, HashMap<String, Object> settings)
	{
		this.uid = uid;
		this.spectator = spectator;
		this.nicked = nicked;
		this.rankStaff = rankStaff;
		this.rankPlayer = rankPlayer;
		this.partyId = partyId;
		this.spectatingTarget = spectatingTarget;
		this.settings = settings;
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
		serializer.writeVarInt(settings.size());
		for(Entry<String, Object> entry : settings.entrySet())
		{
			serializer.writeString(entry.getKey());
			if(entry.getValue() instanceof Integer)
				serializer.writeVarInt((int)entry.getValue());
			else if(entry.getValue() instanceof Boolean)
				serializer.writeVarInt((boolean)entry.getValue() ? Integer.MAX_VALUE - 1 : Integer.MAX_VALUE - 2);
			else
				serializer.writeVarInt(-1);
		}
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
		settings = new HashMap<>();
		int len = deserializer.readVarInt();
		for(int i = 0; i < len; i++)
		{
			String key = deserializer.readString();
			Object value = deserializer.readVarInt();
			if((int)value == Integer.MAX_VALUE - 1 || (int)value == Integer.MAX_VALUE - 2)
				value = (int)value == Integer.MAX_VALUE - 1;
			settings.put(key, value);
		}
	}
	
	public String getUid()
	{
		return uid;
	}
	
	public void setUid(String uid)
	{
		this.uid = uid;
	}
	
	public boolean isSpectator()
	{
		return spectator;
	}
	
	public void setSpectator(boolean spectator)
	{
		this.spectator = spectator;
	}
	
	public boolean isNicked()
	{
		return nicked;
	}
	
	public void setNicked(boolean nicked)
	{
		this.nicked = nicked;
	}
	
	public EnumRankStaff getRankStaff()
	{
		return rankStaff;
	}
	
	public void setRankStaff(EnumRankStaff rankStaff)
	{
		this.rankStaff = rankStaff;
	}
	
	public EnumRankPlayer getRankPlayer()
	{
		return rankPlayer;
	}
	
	public void setRankPlayer(EnumRankPlayer rankPlayer)
	{
		this.rankPlayer = rankPlayer;
	}
	
	public int getPartyId()
	{
		return partyId;
	}
	
	public void setPartyId(int partyId)
	{
		this.partyId = partyId;
	}
	
	public String getSpectatingTarget()
	{
		return spectatingTarget;
	}
	
	public void setSpectatingTarget(String spectatingTarget)
	{
		this.spectatingTarget = spectatingTarget;
	}
	
	public HashMap<String, Object> getSettings()
	{
		return settings;
	}
	
	public void setSettings(HashMap<String, Object> settings)
	{
		this.settings = settings;
	}
	
	public enum EnumRankStaff
	{
		NO_STAFF,
		DEV,
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
		ELITARIA,
		AMI;
	}
}

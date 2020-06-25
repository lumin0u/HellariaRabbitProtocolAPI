package fr.hellaria.protocol.payloads;

import java.io.EOFException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.hellaria.protocol.HellariaPlayer;
import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadPlayerInfo extends Payload
{
	public static final int id = 4;
	
	private String uid;
	private String name;
	private boolean spectator;
	private boolean nicked;
	private EnumRankStaff rankStaff;
	private Map<EnumRankPlayer, Long> rankPlayers;
	private int partyId;
	private String spectatingTarget;
	private HashMap<String, Object> settings;
	private long firstConnection;
	private long lastConnection;
	private int gameTime;
	
	public PayloadPlayerInfo()
	{}
	
	public PayloadPlayerInfo(HellariaPlayer player)
	{
		this.uid = player.getUid();
		this.name = player.getName();
		this.spectator = player.isSpectator();
		this.nicked = player.isNicked();
		this.rankStaff = player.getRankStaff();
		this.rankPlayers = player.getRankPlayers();
		this.partyId = player.getPartyId();
		this.spectatingTarget = player.getSpectatingTarget();
		this.settings = player.getSettings();
		this.firstConnection = player.getFirstConnection();
		this.lastConnection = player.getLastConnection();
		this.gameTime = player.getGameTime();
	}
	
	public PayloadPlayerInfo(String uid, String name, boolean spectator, boolean nicked, EnumRankStaff rankStaff, Map<EnumRankPlayer, Long> rankPlayers, int partyId, String spectatingTarget, HashMap<String, Object> settings, long firstConnection, long lastConnection, int gameTime)
	{
		this.uid = uid;
		this.name = name;
		this.spectator = spectator;
		this.nicked = nicked;
		this.rankStaff = rankStaff;
		this.rankPlayers = rankPlayers;
		this.partyId = partyId;
		this.spectatingTarget = spectatingTarget;
		this.settings = settings;
		this.firstConnection = firstConnection;
		this.lastConnection = lastConnection;
		this.gameTime = gameTime;
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(this.uid);
		serializer.writeString(this.name);
		serializer.writeVarInt(rankPlayers.size());
		for(Entry<EnumRankPlayer, Long> entry : rankPlayers.entrySet())
		{
			serializer.writeVarInt(entry.getKey().ordinal());
			serializer.writeVarLong(entry.getValue());
		}
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
			{
				serializer.writeVarInt(1);
				serializer.writeVarInt((int)entry.getValue());
			}
			else if(entry.getValue() instanceof Boolean)
			{
				serializer.writeVarInt(0);
				serializer.writeBoolean((boolean)entry.getValue());
			}
			else
			{
				serializer.writeVarInt(-1);
				serializer.writeVarInt(-1);
			}
		}
		serializer.writeVarLong(this.firstConnection);
		serializer.writeVarLong(this.lastConnection);
		serializer.writeVarInt(this.gameTime);
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer) throws EOFException
	{
		this.uid = deserializer.readString();
		this.name = deserializer.readString();
		rankPlayers = new HashMap<>();
		int ranksSize = deserializer.readVarInt();
		for(int i = 0; i < ranksSize; i++)
			rankPlayers.put(EnumRankPlayer.values()[deserializer.readVarInt()], deserializer.readVarLong());
		this.rankStaff = EnumRankStaff.values()[deserializer.readVarInt()];
		this.partyId = deserializer.readVarInt();
		this.nicked = deserializer.readBoolean();
		this.spectator = deserializer.readBoolean();
		if(this.spectator)
			this.spectatingTarget = deserializer.readString();
		settings = new HashMap<>();
		int len = deserializer.readVarInt();
		for(int i = 0; i < len; i++)
		{
			String key = deserializer.readString();
			int type = deserializer.readVarInt();
			if(type == 0)
				settings.put(key, deserializer.readBoolean());
			else if(type == 1)
				settings.put(key, deserializer.readVarInt());
			else
				settings.put(key, deserializer.readVarInt());
		}
		this.firstConnection = deserializer.readVarLong();
		this.lastConnection = deserializer.readVarLong();
		this.gameTime = deserializer.readVarInt();
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
	
	public Map<EnumRankPlayer, Long> getRankPlayers()
	{
		return rankPlayers;
	}
	
	public void setRankPlayers(Map<EnumRankPlayer, Long> rankPlayers)
	{
		this.rankPlayers = rankPlayers;
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
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public long getFirstConnection()
	{
		return firstConnection;
	}
	
	public void setFirstConnection(long firstConnection)
	{
		this.firstConnection = firstConnection;
	}
	
	public long getLastConnection()
	{
		return lastConnection;
	}
	
	public void setLastConnection(long lastConnection)
	{
		this.lastConnection = lastConnection;
	}
	
	public int getGameTime()
	{
		return gameTime;
	}
	
	public void setGameTime(int gameTime)
	{
		this.gameTime = gameTime;
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

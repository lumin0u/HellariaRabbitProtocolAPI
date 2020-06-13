package fr.hellaria.protocol;

import java.util.HashMap;
import java.util.Map;

import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo.EnumRankPlayer;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo.EnumRankStaff;

public class HellariaPlayer
{
	private String uid;
	private String name;
	private boolean spectator;
	private boolean nicked;
	private PayloadPlayerInfo.EnumRankStaff rankStaff;
	private Map<EnumRankPlayer, Long> rankPlayers;
	private int partyId;
	private String spectatingTarget;
	private HashMap<String, Object> settings;
	private long firstConnection;
	private long lastConnection;
	private int gameTime;
	
	public HellariaPlayer(String uid, String name, boolean spectator, boolean nicked, EnumRankStaff rankStaff, Map<EnumRankPlayer, Long> rankPlayers, int partyId, String spectatingTarget, HashMap<String, Object> settings, long firstConnection, long lastConnection, int gameTime)
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
	
	public String getUid()
	{
		return this.uid;
	}
	
	public void setUid(String uid)
	{
		this.uid = uid;
	}
	
	public boolean isSpectator()
	{
		return this.spectator;
	}
	
	public void setSpectator(boolean spectator)
	{
		this.spectator = spectator;
	}
	
	public boolean isNicked()
	{
		return this.nicked;
	}
	
	public void setNicked(boolean nicked)
	{
		this.nicked = nicked;
	}
	
	public PayloadPlayerInfo.EnumRankStaff getRankStaff()
	{
		return this.rankStaff;
	}
	
	public void setRankStaff(PayloadPlayerInfo.EnumRankStaff rankStaff)
	{
		this.rankStaff = rankStaff;
	}
	
	public Map<EnumRankPlayer, Long> getRankPlayers()
	{
		return this.rankPlayers;
	}
	
	public void setRankPlayers(Map<EnumRankPlayer, Long> rankPlayers)
	{
		this.rankPlayers = rankPlayers;
	}
	
	public int getPartyId()
	{
		return this.partyId;
	}
	
	public void setPartyId(int partyId)
	{
		this.partyId = partyId;
	}
	
	public String getSpectatingTarget()
	{
		return this.spectatingTarget;
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
}

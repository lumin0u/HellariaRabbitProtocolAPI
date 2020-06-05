package fr.hellaria.protocol;

import java.util.HashMap;

import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo.EnumRankPlayer;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo.EnumRankStaff;

public class HellariaPlayer
{
	private String uid;
	private boolean spectator;
	private boolean nicked;	
	private PayloadPlayerInfo.EnumRankStaff rankStaff;
	private PayloadPlayerInfo.EnumRankPlayer rankPlayer;
	private int partyId;
	private String spectatingTarget;
	private HashMap<String, Object> settings;
	
	public HellariaPlayer(String uid, boolean spectator, boolean nicked, EnumRankStaff rankStaff, EnumRankPlayer rankPlayer, int partyId, String spectatingTarget, HashMap<String, Object> settings)
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
	
	public PayloadPlayerInfo.EnumRankPlayer getRankPlayer()
	{
		return this.rankPlayer;
	}
	
	public void setRankPlayer(PayloadPlayerInfo.EnumRankPlayer rankPlayer)
	{
		this.rankPlayer = rankPlayer;
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
}

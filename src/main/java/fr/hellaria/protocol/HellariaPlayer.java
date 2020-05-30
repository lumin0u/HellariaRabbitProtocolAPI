package fr.hellaria.protocol;

import fr.hellaria.protocol.payloads.PayloadPlayerInfo;

public class HellariaPlayer
{
	private String uid;
	private boolean spectator;
	private boolean nicked;
	private PayloadPlayerInfo.EnumRankStaff rankStaff;
	private PayloadPlayerInfo.EnumRankPlayer rankPlayer;
	private int partyId;
	private String spectatingTarget;
	
	public HellariaPlayer(String uid, boolean spectator, boolean nicked, PayloadPlayerInfo.EnumRankStaff rankStaff, PayloadPlayerInfo.EnumRankPlayer rankPlayer, int partyId, String spectatingTarget)
	{
		this.uid = uid;
		this.spectator = spectator;
		this.nicked = nicked;
		this.rankStaff = rankStaff;
		this.rankPlayer = rankPlayer;
		this.partyId = partyId;
		this.spectatingTarget = spectatingTarget;
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
}

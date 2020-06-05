package fr.hellaria.protocol;

import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.payloads.PayloadServerInfo.EnumServerStatus;

public class HellariaServer
{
	String name;
	int maxPlayerCount;
	@Deprecated
	boolean parties;
	EnumServerStatus status;
	List<String> players;
	List<String> spectators;
	
	public HellariaServer(String name, int maxPlayerCount, boolean parties, EnumServerStatus status, List<String> players, List<String> spectators)
	{
		this.name = name;
		this.maxPlayerCount = maxPlayerCount;
		this.parties = parties;
		this.status = status;
		this.players = new ArrayList<>(players);
		this.spectators = new ArrayList<>(spectators);
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getMaxPlayerCount()
	{
		return maxPlayerCount;
	}
	
	@Deprecated
	public boolean isParties()
	{
		return parties;
	}
	
	public EnumServerStatus getStatus()
	{
		return status;
	}
	
	public void setMaxPlayerCount(int maxPlayerCount)
	{
		this.maxPlayerCount = maxPlayerCount;
	}
	
	@Deprecated
	public void setParties(boolean parties)
	{
		this.parties = parties;
	}
	
	public void setStatus(EnumServerStatus status)
	{
		this.status = status;
	}
	
	public List<String> getPlayers()
	{
		return players;
	}
	
	public List<String> getSpectators()
	{
		return spectators;
	}
}

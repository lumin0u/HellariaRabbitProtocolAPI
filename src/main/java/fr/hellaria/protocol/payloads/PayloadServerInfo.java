package fr.hellaria.protocol.payloads;

import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.HellariaServer;
import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadServerInfo extends Payload
{
	private String name;
	private int playerCount, maxPlayerCount, spectatorCount;
	private EnumServerStatus status;
	private List<String> players, spectators;
	
	public PayloadServerInfo()
	{
		
	}

	public PayloadServerInfo(HellariaServer server)
	{
		this.name = server.getName();
		this.players = new ArrayList<>(server.getPlayers());
		this.spectators = new ArrayList<>(server.getSpectators());
		this.playerCount = players.size();
		this.spectatorCount = spectators.size();
		this.maxPlayerCount = server.getMaxPlayerCount();
		this.status = server.getStatus();
	}

	public PayloadServerInfo(String name, int maxPlayerCount, EnumServerStatus status, List<String> players, List<String> spectators)
	{
		this.name = name;
		this.playerCount = players.size();
		this.maxPlayerCount = maxPlayerCount;
		this.spectatorCount = spectators.size();
		this.status = status;
		this.players = new ArrayList<>(players);
		this.spectators = new ArrayList<>(spectators);
	}

	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(name);
		serializer.writeVarInt(status.ordinal());
		serializer.writeVarInt(maxPlayerCount);
		serializer.writeVarInt(playerCount);
		for(String s : players)
			serializer.writeString(s);
		serializer.writeVarInt(spectatorCount);
		for(String s : spectators)
			serializer.writeString(s);
		
	}

	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		name = deserializer.readString();
		status = EnumServerStatus.values()[deserializer.readVarInt()];
		maxPlayerCount = deserializer.readVarInt();
		playerCount = deserializer.readVarInt();
		players = new ArrayList<>();
		for(int i = 0; i < playerCount; i++)
			players.add(deserializer.readString());
		spectatorCount = deserializer.readVarInt();
		for(int i = 0; i < spectatorCount; i++)
			spectators.add(deserializer.readString());
	}
	
	public String getName()
	{
		return name;
	}

	public int getPlayerCount()
	{
		return playerCount;
	}

	public int getMaxPlayerCount()
	{
		return maxPlayerCount;
	}

	public int getSpectatorsCount()
	{
		return spectatorCount;
	}

	public EnumServerStatus getStatus()
	{
		return status;
	}
	
	public static enum EnumServerStatus
	{
		@Deprecated
		ONLINE,
		OFFLINE;
	}
}

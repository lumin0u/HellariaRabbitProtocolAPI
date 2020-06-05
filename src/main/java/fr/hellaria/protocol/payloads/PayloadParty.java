package fr.hellaria.protocol.payloads;

import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadParty extends Payload
{
	private int id;
	private List<String> players;
	private String leader;
	
	public PayloadParty()
	{
		
	}
	
	public PayloadParty(int id, List<String> players, String leader)
	{
		this.id = id;
		this.players = players;
		this.leader = leader;
	}


	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeVarInt(id);
		serializer.writeString(leader);
		serializer.writeVarInt(players.size());
		for(String pl : players)
			serializer.writeString(pl);
	}

	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		id = deserializer.readVarInt();
		leader = deserializer.readString();
		players = new ArrayList<>();
		int len = deserializer.readVarInt();
		for(int i = 0; i < len; i++)
			players.add(deserializer.readString());
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public List<String> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<String> players)
	{
		this.players = players;
	}

	public String getLeader()
	{
		return leader;
	}

	public void setLeader(String leader)
	{
		this.leader = leader;
	}
}

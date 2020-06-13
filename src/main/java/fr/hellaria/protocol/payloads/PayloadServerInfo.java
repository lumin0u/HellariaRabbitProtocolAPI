package fr.hellaria.protocol.payloads;

import fr.hellaria.protocol.HellariaServer;
import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadServerInfo extends Payload
{
	private String name;
	private EnumServerStatus status;
//	private List<String> players, spectators;
	
	public PayloadServerInfo()
	{
		
	}
	
	public PayloadServerInfo(HellariaServer server)
	{
		this.name = server.getName();
//		this.players = new ArrayList<>(server.getPlayers());
//		this.spectators = new ArrayList<>(server.getSpectators());
		this.status = server.getStatus();
	}
	
	public PayloadServerInfo(String name, EnumServerStatus status)//, List<String> players, List<String> spectators)
	{
		this.name = name;
		this.status = status;
//		this.players = new ArrayList<>(players);
//		this.spectators = new ArrayList<>(spectators);
	}
	
	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeString(name);
		serializer.writeVarInt(status.ordinal());
//		serializer.writeVarInt(players.size());
//		for(String s : players)
//			serializer.writeString(s);
//		serializer.writeVarInt(spectators.size());
//		for(String s : spectators)
//			serializer.writeString(s);
		
	}
	
	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		name = deserializer.readString();
		status = EnumServerStatus.values()[deserializer.readVarInt()];
//		players = new ArrayList<>();
//		for(int i = 0; i < deserializer.readVarInt(); i++)
//			players.add(deserializer.readString());
//		for(int i = 0; i < deserializer.readVarInt(); i++)
//			spectators.add(deserializer.readString());
	}
	
	public String getName()
	{
		return name;
	}
	
	public EnumServerStatus getStatus()
	{
		return status;
	}
	
//	public List<String> getPlayers()
//	{
//		return new ArrayList<>(players);
//	}
//	
//	public List<String> getSpectators()
//	{
//		return new ArrayList<>(spectators);
//	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setStatus(EnumServerStatus status)
	{
		this.status = status;
	}
	
//	public void setPlayers(List<String> players)
//	{
//		this.players = players;
//	}
//	
//	public void setSpectators(List<String> spectators)
//	{
//		this.spectators = spectators;
//	}
	
	public static enum EnumServerStatus
	{
		@Deprecated
		ONLINE,
		WAITING,
		IN_GAME,
		RESTARTING,
		STARTING,
		OFFLINE;
	}
}

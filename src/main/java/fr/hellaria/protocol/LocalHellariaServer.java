package fr.hellaria.protocol;

import java.util.List;

import fr.hellaria.protocol.payloads.PayloadServerInfo.EnumServerStatus;

public class LocalHellariaServer extends HellariaServer
{
	static boolean exists = false;
	
	public LocalHellariaServer(String name, int maxPlayerCount, boolean parties, EnumServerStatus status, List<String> players, List<String> spectators)
	{
		super(name, maxPlayerCount, parties, status, players, spectators);
		if(exists)
			throw new IllegalStateException("Il ne peut y avoir qu'un seul serveur local");
		exists = true;
	}
}

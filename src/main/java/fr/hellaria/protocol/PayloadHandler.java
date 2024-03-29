package fr.hellaria.protocol;

import fr.hellaria.protocol.payloads.PayloadAskServers;
import fr.hellaria.protocol.payloads.PayloadFriends;
import fr.hellaria.protocol.payloads.PayloadHandshake;
import fr.hellaria.protocol.payloads.PayloadMonarias;
import fr.hellaria.protocol.payloads.PayloadOnlineCount;
import fr.hellaria.protocol.payloads.PayloadParty;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerNicked;
import fr.hellaria.protocol.payloads.PayloadRestart;
import fr.hellaria.protocol.payloads.PayloadSendToServer;
import fr.hellaria.protocol.payloads.PayloadServerInfo;
import fr.hellaria.protocol.payloads.PayloadServerTypeAndName;

@SuppressWarnings("unused")
public abstract class PayloadHandler
{
	public void handlePayloadStop()
	{}
	
	public void handleHandshake(PayloadHandshake payload, String source)
	{}
	
	public void handleServerInfo(PayloadServerInfo payload, String source)
	{}
	
	public void handlePlayerInfo(PayloadPlayerInfo payload, String source)
	{}
	
	public void handlePlayerNicked(PayloadPlayerNicked payload, String source)
	{}
	
	public void handleServerTypeAndGame(PayloadServerTypeAndName payload, String source)
	{}
	
	public void handleParty(PayloadParty payload, String source)
	{}
	
	public void handleMonarias(PayloadMonarias payload, String source)
	{}
	
	public void handleSendToServer(PayloadSendToServer payload, String source)
	{}
	
	public void handleRestart(PayloadRestart payload, String source)
	{}
	
	public void handleFriends(PayloadFriends payload, String source)
	{}
	
	public void handleAskServers(PayloadAskServers payload, String source)
	{}
	
	public void handlePlayerCount(PayloadOnlineCount payload, String source)
	{}
}

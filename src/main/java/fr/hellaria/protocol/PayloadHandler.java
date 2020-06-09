package fr.hellaria.protocol;

import fr.hellaria.protocol.payloads.PayloadFriends;
import fr.hellaria.protocol.payloads.PayloadMonarias;
import fr.hellaria.protocol.payloads.PayloadParty;
import fr.hellaria.protocol.payloads.PayloadPlayerAskPosition;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerNicked;
import fr.hellaria.protocol.payloads.PayloadPlayerPosition;
import fr.hellaria.protocol.payloads.PayloadRestart;
import fr.hellaria.protocol.payloads.PayloadSendToServer;
import fr.hellaria.protocol.payloads.PayloadServerInfo;
import fr.hellaria.protocol.payloads.PayloadServerTypeAndGame;

public interface PayloadHandler
{
	public void handlePayloadStop();
	
	public void handleHandshake(String source);
	
	public void handleServerInfo(PayloadServerInfo payload, String source);
	
	public void handlePlayerInfo(PayloadPlayerInfo payload, String source);
	
	public void handlePlayerNicked(PayloadPlayerNicked payload, String source);
	
	public void handleServerTypeAndGame(PayloadServerTypeAndGame payload, String source);
	
	public void handleParty(PayloadParty payload, String source);
	
	public void handleMonarias(PayloadMonarias payload, String source);
	
	public void handleSendToServer(PayloadSendToServer payload, String source);
	
	public void handleRestart(PayloadRestart payload, String source);
	
	public void handleFriends(PayloadFriends payload, String source);
	
	public void handleAskPosition(PayloadPlayerAskPosition payload, String source);
	
	public void handlePlayerPosition(PayloadPlayerPosition payload, String source);
}

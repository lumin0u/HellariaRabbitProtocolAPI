package fr.hellaria.protocol;

import fr.hellaria.protocol.payloads.PayloadMonarias;
import fr.hellaria.protocol.payloads.PayloadParty;
import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerNicked;
import fr.hellaria.protocol.payloads.PayloadRestart;
import fr.hellaria.protocol.payloads.PayloadSendToHub;
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
	
	public void handleSendToHub(PayloadSendToHub payload, String source);
	
	public void handleRestart(PayloadRestart payload, String source);
}

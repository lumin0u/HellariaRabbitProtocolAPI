package fr.hellaria.protocol;

import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadPlayerNicked;
import fr.hellaria.protocol.payloads.PayloadServerInfo;
import fr.hellaria.protocol.payloads.PayloadServerTypeAndGame;

public interface PayloadHandler
{
	public void handlePayloadStop();
	
	public void handleServerInfo(PayloadServerInfo payload, String source);
	
	public void handlePlayerInfo(PayloadPlayerInfo payload, String source);
	
	public void handlePlayerNicked(PayloadPlayerNicked payload, String source);
	
	public void handleServerTypeAndGame(PayloadServerTypeAndGame payload, String source);
}

package fr.hellaria.protocol;

import fr.hellaria.protocol.payloads.PayloadPlayerInfo;
import fr.hellaria.protocol.payloads.PayloadServerInfo;

public interface PayloadHandler
{
	public void handleServerInfo(PayloadServerInfo payload, String source);
	
	public void handlePlayerInfo(PayloadPlayerInfo payload, String source);
}

package fr.hellaria.protocol;

import fr.hellaria.protocol.payloads.PayloadServerInfo;

public interface PayloadHandler
{
	public void handleServerInfo(PayloadServerInfo payload);
}

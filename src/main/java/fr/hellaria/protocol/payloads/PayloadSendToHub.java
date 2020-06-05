package fr.hellaria.protocol.payloads;

import java.util.ArrayList;
import java.util.List;

import fr.hellaria.protocol.PayloadDeserializer;
import fr.hellaria.protocol.PayloadSerializer;

public class PayloadSendToHub extends Payload
{
	private List<String> uuids;
	
	public PayloadSendToHub()
	{
	}

	public PayloadSendToHub(List<String> uuids)
	{
		this.uuids = uuids;
	}

	@Override
	public void serialize(PayloadSerializer serializer)
	{
		serializer.writeVarInt(uuids.size());
		uuids.forEach(uuid -> serializer.writeString(uuid));
	}

	@Override
	public void deserialize(PayloadDeserializer deserializer)
	{
		uuids = new ArrayList<>();
		for(int i = 0; i < deserializer.readVarInt(); i++)
			uuids.add(deserializer.readString());
	}

	public List<String> getUuids()
	{
		return uuids;
	}

	public void setUuids(List<String> uuids)
	{
		this.uuids = uuids;
	}
}

package be.kdg.prog6.customer.adapters.in.amqp;

import be.kdg.prog6.common.events.EventType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public interface AMQPReceiver {

    boolean apply(EventType eventType);

    void receiveMessage(JsonNode body) throws JsonProcessingException;
}

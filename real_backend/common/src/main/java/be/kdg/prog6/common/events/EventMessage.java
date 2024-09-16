package be.kdg.prog6.common.events;

import com.fasterxml.jackson.databind.JsonNode;

public record EventMessage(EventHeader eventHeader, JsonNode messageBody) {

}

package be.kdg.prog6.customer.adapters.in.amqp;

import be.kdg.prog6.common.events.EventType;
import be.kdg.prog6.common.events.RideEndedEvent;
import be.kdg.prog6.customer.config.RabbitMQQueueConfig;
import be.kdg.prog6.customer.ports.in.CreateRideCommand;
import be.kdg.prog6.customer.ports.in.CreateRideUseCase;
import be.kdg.prog6.customer.ports.out.RideCreatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RideAMQPReceiver implements AMQPReceiver {
    private final Logger logger = LoggerFactory.getLogger(RideAMQPReceiver.class);
    private final ObjectMapper objectMapper;

    private final CreateRideUseCase createRideUseCase;

    public RideAMQPReceiver(ObjectMapper objectMapper, CreateRideUseCase createRideUseCase) {
        this.objectMapper = objectMapper;
        this.createRideUseCase = createRideUseCase;
    }

    @Override
    public boolean apply(EventType eventType) {
        return eventType.equals(EventType.RideEndedEvent);
    }

    @Override
    public void receiveMessage(JsonNode message) throws JsonProcessingException {
        logger.info("Collecting ride via fanout");
        RideEndedEvent event = objectMapper.treeToValue(message, RideEndedEvent.class);
        createRideUseCase.createRide(new CreateRideCommand(event.startTime(), event.startStation(), event.endTime(), event.endStation(), event.rideUUID(), event.customerUUID()));
    }
}

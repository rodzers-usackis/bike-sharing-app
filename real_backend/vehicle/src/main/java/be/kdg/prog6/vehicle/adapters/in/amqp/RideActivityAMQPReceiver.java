package be.kdg.prog6.vehicle.adapters.in.amqp;

import be.kdg.prog6.common.events.EventType;
import be.kdg.prog6.common.events.RideActivityEvent;
import be.kdg.prog6.vehicle.config.RabbitMQQueueConfig;
import be.kdg.prog6.vehicle.ports.in.ChangeVehicleStateUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RideActivityAMQPReceiver implements AMQPReceiver {
    private final Logger logger = LoggerFactory.getLogger(RideActivityAMQPReceiver.class);
    private final ObjectMapper objectMapper;

    private final ChangeVehicleStateUseCase changeVehicleStateUseCase;

    public RideActivityAMQPReceiver(ObjectMapper objectMapper, ChangeVehicleStateUseCase changeVehicleStateUseCase) {
        this.objectMapper = objectMapper;
        this.changeVehicleStateUseCase = changeVehicleStateUseCase;
    }

    @Override
    public boolean apply(EventType eventType) {
        return eventType.equals(EventType.RideActivityEvent);
    }

    @Override
    public void receiveMessage(JsonNode message) throws JsonProcessingException {
        logger.info("Collecting ride activity via fanout to change vehicle state");
        RideActivityEvent event = objectMapper.treeToValue(message, RideActivityEvent.class);
        changeVehicleStateUseCase.changeVehicleState(event);
    }

}

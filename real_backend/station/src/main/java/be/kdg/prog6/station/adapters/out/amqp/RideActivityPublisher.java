package be.kdg.prog6.station.adapters.out.amqp;

import be.kdg.prog6.common.events.EventHeader;
import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.common.events.EventType;
import be.kdg.prog6.common.events.RideActivityEvent;
import be.kdg.prog6.station.config.RabbitMQModuleConfig;
import be.kdg.prog6.station.domain.DockAction;
import be.kdg.prog6.station.ports.out.RideActivityCreatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RideActivityPublisher implements RideActivityCreatePort {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public RideActivityPublisher(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void createRideActivity(UUID vehicleUUID, UUID customerUUID, DockAction action, UUID dockUUID) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQModuleConfig.fanoutExchange, "",
                    objectMapper.writeValueAsString(new EventMessage(new EventHeader(UUID.randomUUID(), EventType.RideActivityEvent, LocalDateTime.now()),
                            objectMapper.valueToTree(new RideActivityEvent(vehicleUUID,customerUUID,action.name(),dockUUID)))));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

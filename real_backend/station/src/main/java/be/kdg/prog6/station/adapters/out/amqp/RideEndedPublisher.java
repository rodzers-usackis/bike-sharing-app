package be.kdg.prog6.station.adapters.out.amqp;

import be.kdg.prog6.common.events.*;
import be.kdg.prog6.station.config.RabbitMQModuleConfig;
import be.kdg.prog6.station.ports.out.RideCreatePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class RideEndedPublisher implements RideCreatePort {
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;

    public RideEndedPublisher(ObjectMapper objectMapper, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void createRide(LocalDateTime startTime, String startStation, LocalDateTime endTime, String endStation, UUID rideUUID, UUID customerUUID) {
        try {
            rabbitTemplate.convertAndSend(RabbitMQModuleConfig.rideFanoutExchange, "",
                    objectMapper.writeValueAsString(new EventMessage(new EventHeader(UUID.randomUUID(), EventType.RideEndedEvent, LocalDateTime.now()),
                            objectMapper.valueToTree(new RideEndedEvent(startTime,startStation,endTime,endStation, rideUUID, customerUUID)))));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

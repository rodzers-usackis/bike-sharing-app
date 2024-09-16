package be.kdg.prog6.customer.adapters.in.amqp;

import be.kdg.prog6.common.events.EventMessage;
import be.kdg.prog6.customer.config.RabbitMQQueueConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("CustomerFanInReceiver")
public class FanInReceiver {

    private final List<AMQPReceiver> receivers;
    private final ObjectMapper objectMapper;

    public FanInReceiver(List<AMQPReceiver> receivers, ObjectMapper objectMapper) {
        this.receivers = receivers;
        this.objectMapper = objectMapper;
    }

    @RabbitListener(queues = RabbitMQQueueConfig.rideFanoutQueue)
    public void receiveMessage(String message) throws JsonProcessingException {
        EventMessage eventMessage = objectMapper.readValue(message, EventMessage.class);
        receivers.stream().filter(r -> r.apply(eventMessage.eventHeader().eventType())).forEach(r -> {
            try {
                r.receiveMessage(eventMessage.messageBody());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }

}

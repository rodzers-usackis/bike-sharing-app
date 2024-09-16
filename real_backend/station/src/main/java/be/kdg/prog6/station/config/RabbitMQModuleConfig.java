package be.kdg.prog6.station.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQModuleConfig {
    public static final String fanoutExchange = "kdg-fanout";
    public static final String topicExchange = "kdg-topic";

    public static final String dlqExchange = "dlq-direct";

    public static final String rideFanoutExchange = "ride-fanout";
}

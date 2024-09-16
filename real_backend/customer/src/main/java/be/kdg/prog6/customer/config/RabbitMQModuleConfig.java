package be.kdg.prog6.customer.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("CustomerRabbitMQModuleConfig")
public class RabbitMQModuleConfig {

    public static final String fanoutExchange = "kdg-fanout";
    public static final String topicExchange = "kdg-topic";
    public static final String dlqExchange = "dlq-direct";
}

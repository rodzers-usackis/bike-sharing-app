package be.kdg.prog6;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String fanoutExchange = "kdg-fanout";
    public static final String rideFanoutExchange = "ride-fanout";
    public static final String topicExchange = "kdg-topic";
    public static final String dlqExchange = "dlq-direct";

    @Bean
    FanoutExchange rideFanoutExchange() {
        return new FanoutExchange(rideFanoutExchange);
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    DirectExchange dlqExchange() {
        return new DirectExchange(dlqExchange);
    }

}

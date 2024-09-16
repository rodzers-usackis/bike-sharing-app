package be.kdg.prog6;

import be.kdg.prog6.vehicle.config.RabbitMQModuleConfig;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("AppRabbitMQQueueConfig")
public class RabbitMQQueueConfig {

    public static final String fanoutQueue = "grandparents-fanoutqueue";
    public static final String piggybankQueue = "piggybank-topicqueue";
    public static final String activityQueue = "activity-topicqueue";
    public static final String dlqQueue = "dlq-directqueue";
    public static final String defectReportQueue = "defectreport-topicqueue";

    public static final String DLQ_ROUTING_KEY = "dlq";

    public static final String rideFanoutQueue = "ride-fanoutqueue";


    @Bean("rideFanoutQueue")
    Queue ridefanoutQueue() {
        return QueueBuilder.nonDurable(rideFanoutQueue).build();
    }

    @Bean("fanoutQueue")
    Queue fanoutQueue() {
        return QueueBuilder.nonDurable(fanoutQueue).build();
    }

    @Bean("defectReportQueue")
    Queue defectReportQueue() {
        return QueueBuilder.nonDurable(defectReportQueue).build();
    }

    @Bean
    Binding vehicleDefectReportBinding(Queue defectReportQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(defectReportQueue).to(topicExchange).with("routing.customer.object.#");
    }


    @Bean
    Binding rideFanoutBinding(Queue rideFanoutQueue, FanoutExchange rideFanoutExchange) {
        return BindingBuilder.bind(rideFanoutQueue).to(rideFanoutExchange);
    }
    @Bean
    Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

//
//    @Bean
//    Queue dlqQueue() {
//        return QueueBuilder.nonDurable(dlqQueue).build();
//    }
//
//    @Bean
//    Queue activityQueue() {
//        return QueueBuilder.nonDurable(activityQueue)
//                .withArgument("x-dead-letter-routing-key", DLQ_ROUTING_KEY)
//                .withArgument("x-dead-letter-exchange", RabbitMQModuleConfig.dlqExchange)
//                .build();
//    }
//
//    @Bean
//    Queue piggybankQueue() {
//        return QueueBuilder.nonDurable(piggybankQueue).build();
//    }
//
//
//    @Bean
//    Binding DLQbinding(DirectExchange dlqExchange, Queue dlqQueue) {
//        return BindingBuilder.bind(dlqQueue).to(dlqExchange).with(DLQ_ROUTING_KEY);
//    }

//    @Bean
//    Binding ActivityBinding(Queue activityQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(activityQueue).to(topicExchange).with(PiggyBankActivityCreatedEvent.ROUTING_KEY);
//    }
//
//    @Bean
//    Binding PiggyBankBinding(Queue piggybankQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(piggybankQueue).to(topicExchange).with(FamilyReceivedPiggyBankEvent.ROUTING_KEY);
//    }

}


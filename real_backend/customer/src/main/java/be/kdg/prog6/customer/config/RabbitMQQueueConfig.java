package be.kdg.prog6.customer.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("CustomerRabbitMQQueueConfig")
public class RabbitMQQueueConfig {

    public static final String fanoutQueue = "grandparents-fanoutqueue";
    public static final String piggybankQueue = "piggybank-topicqueue";
    public static final String activityQueue = "activity-topicqueue";
    public static final String dlqQueue = "dlq-directqueue";
    public static final String defectReportQueue = "defectreport-topicqueue";


    public static final String DLQ_ROUTING_KEY = "dlq";

    public static final String rideFanoutQueue = "ride-fanoutqueue";

}


package be.kdg.prog6.customer.adapters.out.amqp;

import be.kdg.prog6.common.events.CustomerVehicleDefectReportCreatedEvent;
import be.kdg.prog6.customer.config.RabbitMQModuleConfig;
import be.kdg.prog6.customer.ports.out.DefectReportCreatedEventSendPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class DefectReportCreatedEventSendAdapter implements DefectReportCreatedEventSendPort {

    private final RabbitTemplate rabbitTemplate;

    public DefectReportCreatedEventSendAdapter(org.springframework.amqp.rabbit.core.RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendDefectReportCreatedEvent(CustomerVehicleDefectReportCreatedEvent event) {
    rabbitTemplate.convertAndSend(RabbitMQModuleConfig.topicExchange, "routing.customer.object.customerVehicleDefectReport", event);
    }
}

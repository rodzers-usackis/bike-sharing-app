package be.kdg.prog6.maintenance.adapters.in.amqp;

import be.kdg.prog6.common.events.CustomerVehicleDefectReportCreatedEvent;
import be.kdg.prog6.maintenance.adapters.mapper.DefectReportMapper;
import be.kdg.prog6.maintenance.config.RabbitMQModuleConfig;
import be.kdg.prog6.maintenance.config.RabbitMQQueueConfig;
import be.kdg.prog6.maintenance.ports.in.CreateVehicleDefectReportCommand;
import be.kdg.prog6.maintenance.ports.in.CreateVehicleDefectReportUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerVehicleDefectReportEventReceiverAdapter {

    private final ObjectMapper objectMapper;
    private final CreateVehicleDefectReportUseCase createVehicleDefectReportUseCase;

    public CustomerVehicleDefectReportEventReceiverAdapter(ObjectMapper objectMapper, CreateVehicleDefectReportUseCase createVehicleDefectReportUseCase) {
        this.objectMapper = objectMapper;
        this.createVehicleDefectReportUseCase = createVehicleDefectReportUseCase;
    }


    @RabbitListener(queues = RabbitMQQueueConfig.defectReportQueue)
    public void receiveMessage(CustomerVehicleDefectReportCreatedEvent event) {
        CreateVehicleDefectReportCommand command = DefectReportMapper.INSTANCE.mapToCommand(event);
        createVehicleDefectReportUseCase.createVehicleDefectReport(command);
    }

}

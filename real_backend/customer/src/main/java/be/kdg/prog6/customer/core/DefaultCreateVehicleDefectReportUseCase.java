package be.kdg.prog6.customer.core;

import be.kdg.prog6.common.events.CustomerVehicleDefectReportCreatedEvent;
import be.kdg.prog6.customer.adapters.mapper.VehicleDefectReportMapper;
import be.kdg.prog6.customer.domain.Customer;
import be.kdg.prog6.customer.domain.VehicleDefectReport;
import be.kdg.prog6.customer.ports.in.CreateVehicleDefectReportCommand;
import be.kdg.prog6.customer.ports.in.CreateVehicleDefectReportUseCase;
import be.kdg.prog6.customer.ports.out.CreateVehicleDefectReportPort;
import be.kdg.prog6.customer.ports.out.CustomerLoadPort;
import be.kdg.prog6.customer.ports.out.DefectReportCreatedEventSendPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DefaultCreateVehicleDefectReportUseCase implements CreateVehicleDefectReportUseCase {

    private final Logger logger = LoggerFactory.getLogger(DefaultCreateVehicleDefectReportUseCase.class);

    private final CreateVehicleDefectReportPort createVehicleDefectReportPort;
    private final CustomerLoadPort customerLoadPort;
    private final DefectReportCreatedEventSendPort defectReportCreatedEventSendPort;

    public DefaultCreateVehicleDefectReportUseCase(CreateVehicleDefectReportPort createVehicleDefectReportPort, CustomerLoadPort customerLoadPort, DefectReportCreatedEventSendPort defectReportCreatedEventSendPort) {
        this.createVehicleDefectReportPort = createVehicleDefectReportPort;
        this.customerLoadPort = customerLoadPort;
        this.defectReportCreatedEventSendPort = defectReportCreatedEventSendPort;
    }

    @Override
    public VehicleDefectReport createVehicleDefectReport(CreateVehicleDefectReportCommand command) {

//        Customer customer = customerLoadPort.loadCustomer(command.customerId()).orElseThrow();
        Customer customer = customerLoadPort.loadAllCustomers().orElseThrow().get(0);

        logger.debug("Creating vehicle defect report");
        VehicleDefectReport vehicleDefectReport = VehicleDefectReportMapper.INSTANCE.mapToDomain(command);
        vehicleDefectReport.setCustomer(customer);
        vehicleDefectReport.setUuid(UUID.randomUUID());
        logger.debug(String.format("Created vehicle defect report with id %s", vehicleDefectReport.getUuid()));

        CustomerVehicleDefectReportCreatedEvent event = VehicleDefectReportMapper.INSTANCE.mapToEvent(vehicleDefectReport);
        defectReportCreatedEventSendPort.sendDefectReportCreatedEvent(event);
        return createVehicleDefectReportPort.createVehicleDefectReport(vehicleDefectReport);


    }
}

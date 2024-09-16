package be.kdg.prog6.customer.ports.out;

import be.kdg.prog6.common.events.CustomerVehicleDefectReportCreatedEvent;

public interface DefectReportCreatedEventSendPort {
    void sendDefectReportCreatedEvent(CustomerVehicleDefectReportCreatedEvent event);
}

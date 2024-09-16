package be.kdg.prog6.maintenance.adapters.mapper;

import be.kdg.prog6.common.events.CustomerVehicleDefectReportCreatedEvent;
import be.kdg.prog6.maintenance.adapters.dto.DefectReportDTO;
import be.kdg.prog6.maintenance.adapters.out.db.model.DefectReportJpaEntity;
import be.kdg.prog6.maintenance.domain.VehicleDefectReport;
import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;
import be.kdg.prog6.maintenance.ports.in.CreateVehicleDefectReportCommand;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DefectReportMapperTest {

//    private String photo = "data:image/gif;base64,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";
//
//    @Test
//    void eventToCommandMapping() {
//        CustomerVehicleDefectReportCreatedEvent event = new CustomerVehicleDefectReportCreatedEvent(
//                UUID.randomUUID(),
//                UUID.randomUUID(),
//                UUID.randomUUID(),
//                "some test description",
//                true,
//                true,
//                true,
//                true,
//                true,
//                true,
//                photo
//        );
//
//        CreateVehicleDefectReportCommand command = DefectReportMapper.INSTANCE.mapToCommand(event);
//
//        assertEquals(event.vehicleDefectReportId(), command.customerContextReportId());
//        assertEquals(event.vehicleId(), command.vehicleId());
//        assertEquals(event.customerId(), command.customerId());
//        assertEquals(event.description(), command.description());
//        assertEquals(event.isUnusable(), command.isUnusable());
//        assertEquals(event.hasBrokenWheel(), command.hasBrokenWheel());
//        assertEquals(event.hasFlatTyre(), command.hasFlatTyre());
//        assertEquals(event.hasBrokenLight(), command.hasBrokenLight());
//        assertEquals(event.hasBrokenBrake(), command.hasBrokenBrake());
//        assertEquals(event.hasOtherDefect(), command.hasOtherDefect());
//        assertEquals(event.photo(), command.photo());
//
//    }
//
//    @Test
//    void domainToJpaMapping() {
//        VehicleDefectReport report = new VehicleDefectReport();
//        report.setUuid(UUID.randomUUID());
//        report.setCustomerId(UUID.randomUUID());
//        report.setVehicleId(UUID.randomUUID());
//        report.setDescription("some test description");
//        report.setTimestamp(LocalDateTime.now());
//        report.setUnusable(true);
//        report.setHasBrokenBrake(true);
//        report.setHasBrokenLight(true);
//        report.setHasBrokenWheel(true);
//        report.setHasFlatTyre(true);
//        report.setHasOtherDefect(true);
//        report.setPhoto(photo);
//        report.setStatus(VehicleDefectReportStatus.OPEN);
//
//        DefectReportJpaEntity entity = DefectReportMapper.INSTANCE.mapToJpaEntity(report);
//
//        assertEquals(report.getUuid(), entity.getUuid());
//        assertEquals(report.getCustomerId(), entity.getCustomerId());
//        assertEquals(report.getVehicleId(), entity.getVehicleId());
//        assertEquals(report.getDescription(), entity.getDescription());
//        assertEquals(report.getTimestamp(), entity.getTimestamp());
//        assertEquals(report.isUnusable(), entity.isUnusable());
//        assertEquals(report.isHasBrokenBrake(), entity.isHasBrokenBrake());
//        assertEquals(report.isHasBrokenLight(), entity.isHasBrokenLight());
//        assertEquals(report.isHasBrokenWheel(), entity.isHasBrokenWheel());
//        assertEquals(report.isHasFlatTyre(), entity.isHasFlatTyre());
//        assertEquals(report.isHasOtherDefect(), entity.isHasOtherDefect());
//        assertEquals(report.getPhoto(), entity.getPhoto());
//        assertEquals(report.getStatus(), entity.getStatus());
//
//
//    }
//
//    @Test
//    void jpaToDomainMapping() {
//        DefectReportJpaEntity entity = new DefectReportJpaEntity();
//        entity.setUuid(UUID.randomUUID());
//        entity.setCustomerId(UUID.randomUUID());
//        entity.setVehicleId(UUID.randomUUID());
//        entity.setDescription("some test description");
//        entity.setTimestamp(LocalDateTime.now());
//        entity.setUnusable(true);
//        entity.setHasBrokenBrake(true);
//        entity.setHasBrokenLight(true);
//        entity.setHasBrokenWheel(true);
//        entity.setHasFlatTyre(true);
//        entity.setHasOtherDefect(true);
//        entity.setPhoto(photo);
//        entity.setStatus(VehicleDefectReportStatus.OPEN);
//
//        VehicleDefectReport report = DefectReportMapper.INSTANCE.mapToDomain(entity);
//
//        assertEquals(entity.getUuid(), report.getUuid());
//        assertEquals(entity.getCustomerId(), report.getCustomerId());
//        assertEquals(entity.getVehicleId(), report.getVehicleId());
//        assertEquals(entity.getDescription(), report.getDescription());
//        assertEquals(entity.getTimestamp(), report.getTimestamp());
//        assertEquals(entity.isUnusable(), report.isUnusable());
//        assertEquals(entity.isHasBrokenBrake(), report.isHasBrokenBrake());
//        assertEquals(entity.isHasBrokenLight(), report.isHasBrokenLight());
//        assertEquals(entity.isHasBrokenWheel(), report.isHasBrokenWheel());
//        assertEquals(entity.isHasFlatTyre(), report.isHasFlatTyre());
//        assertEquals(entity.isHasOtherDefect(), report.isHasOtherDefect());
//        assertEquals(entity.getPhoto(), report.getPhoto());
//        assertEquals(entity.getStatus(), report.getStatus());
//    }
//
//    @Test
//    void domainToDTOMapping() {
//        VehicleDefectReport report = new VehicleDefectReport();
//        report.setUuid(UUID.randomUUID());
//        report.setCustomerId(UUID.randomUUID());
//        report.setVehicleId(UUID.randomUUID());
//        report.setDescription("some test description");
//        report.setTimestamp(LocalDateTime.now());
//        report.setUnusable(true);
//        report.setHasBrokenBrake(true);
//        report.setHasBrokenLight(true);
//        report.setHasBrokenWheel(true);
//        report.setHasFlatTyre(true);
//        report.setHasOtherDefect(true);
//        report.setPhoto(photo);
//        report.setStatus(VehicleDefectReportStatus.OPEN);
//
//        DefectReportDTO dto = DefectReportMapper.INSTANCE.mapToDTO(report);
//
//        assertEquals(report.getUuid(), dto.uuid());
//        assertEquals(report.getCustomerId().toString(), dto.customerId());
//        assertEquals(report.getVehicleId().toString(), dto.vehicleId());
//        assertEquals(report.getDescription(), dto.description());
//        assertEquals(report.getTimestamp(), dto.timestamp());
//        assertEquals(report.isUnusable(), dto.isUnusable());
//        assertEquals(report.isHasBrokenBrake(), dto.hasBrokenBrake());
//        assertEquals(report.isHasBrokenLight(), dto.hasBrokenLight());
//        assertEquals(report.isHasBrokenWheel(), dto.hasBrokenWheel());
//        assertEquals(report.isHasFlatTyre(), dto.hasFlatTyre());
//        assertEquals(report.isHasOtherDefect(), dto.hasOtherDefect());
//        assertEquals(report.getPhoto(), dto.photo());
//        assertEquals(report.getStatus(), dto.status());
//
//    }


}
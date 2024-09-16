package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.adapters.dto.VehicleDefectReportDTO;
import be.kdg.prog6.customer.adapters.mapper.VehicleDefectReportMapper;
import be.kdg.prog6.customer.adapters.out.db.model.VehicleDefectReportJpaEntity;
import be.kdg.prog6.customer.domain.VehicleDefect;
import be.kdg.prog6.customer.domain.VehicleDefectReport;
import be.kdg.prog6.customer.ports.in.CreateVehicleDefectReportCommand;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class VehicleDefectReportMapperTest {

    private byte[] picture1 = new byte[]{1, 2, 3};
    private byte[] picture2 = new byte[]{5, 6, 7};
    private String photo = "data:image/gif;base64,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";



    @Test
    void domainEntityMappingTest() {

        VehicleDefectReport vehicleDefectReport = new VehicleDefectReport();
        vehicleDefectReport.setDescription("test");
        vehicleDefectReport.setVehicleId(UUID.randomUUID());
        vehicleDefectReport.setUnusable(true);
        vehicleDefectReport.setHasBrokenWheel(true);
        vehicleDefectReport.setHasFlatTyre(true);
        vehicleDefectReport.setUuid(UUID.randomUUID());
        vehicleDefectReport.setPhoto(photo);

        VehicleDefectReportJpaEntity vehicleDefectReportJpaEntity = VehicleDefectReportMapper.INSTANCE.mapToJpa(vehicleDefectReport);

        assertEquals(vehicleDefectReport.getDescription(), vehicleDefectReportJpaEntity.getDescription());
        assertEquals(vehicleDefectReport.getVehicleId().toString(), vehicleDefectReportJpaEntity.getVehicleId());
        assertEquals(vehicleDefectReport.isUnusable(), vehicleDefectReportJpaEntity.isUnusable());
        assertEquals(vehicleDefectReport.isHasBrokenWheel(), vehicleDefectReportJpaEntity.isHasBrokenWheel());
        assertEquals(vehicleDefectReport.isHasFlatTyre(), vehicleDefectReportJpaEntity.isHasFlatTyre());
        assertEquals(vehicleDefectReport.getUuid(), vehicleDefectReportJpaEntity.getUuid());
        assertEquals(vehicleDefectReport.getPhoto(), vehicleDefectReportJpaEntity.getPhoto());


    }

    @Test
    void commandMappingTest() throws Exception{

        VehicleDefectReportDTO dto = new VehicleDefectReportDTO();
        dto.setDescription("test");
        dto.setVehicleId(UUID.randomUUID().toString());
        dto.setHasBrokenBrake(true);
        dto.setHasBrokenLight(true);
        dto.setHasBrokenWheel(false);
        dto.setHasFlatTyre(false);
        dto.setUnusable(true);
        dto.setCustomerId(UUID.randomUUID().toString());
        dto.setPhoto(photo);

        CreateVehicleDefectReportCommand command = VehicleDefectReportMapper.INSTANCE.mapToCommand(dto);

        assertEquals(dto.getDescription(), command.description());
        assertEquals(dto.getVehicleId(), command.vehicleId().toString());
        assertEquals(dto.getCustomerId(), command.customerId().toString());
        assertEquals(dto.isUnusable(), command.isUnusable());
        assertEquals(dto.isHasBrokenBrake(), command.hasBrokenBrake());
        assertEquals(dto.isHasBrokenLight(), command.hasBrokenLight());
        assertEquals(dto.isHasBrokenWheel(), command.hasBrokenWheel());
        assertEquals(dto.isHasFlatTyre(), command.hasFlatTyre());
        assertEquals(dto.isHasOtherDefect(), command.hasOtherDefect());
        assertEquals(dto.isUnusable(), command.isUnusable());
        assertNotNull(command.photo());


    }

    @Test
    void jpaEntityMappingTest() {

        VehicleDefectReportJpaEntity vehicleDefectReportJpaEntity = new VehicleDefectReportJpaEntity();
        vehicleDefectReportJpaEntity.setDescription("test");
        vehicleDefectReportJpaEntity.setVehicleId(UUID.randomUUID().toString());
        vehicleDefectReportJpaEntity.setUnusable(true);
        vehicleDefectReportJpaEntity.setHasBrokenWheel(true);
        vehicleDefectReportJpaEntity.setHasFlatTyre(false);

        VehicleDefectReport vehicleDefectReport = VehicleDefectReportMapper.INSTANCE.mapToDomain(vehicleDefectReportJpaEntity);



        assertEquals(vehicleDefectReportJpaEntity.getDescription(), vehicleDefectReport.getDescription());
        assertEquals(vehicleDefectReportJpaEntity.getVehicleId(), vehicleDefectReport.getVehicleId().toString());
        assertEquals(vehicleDefectReportJpaEntity.isUnusable(), vehicleDefectReport.isUnusable());
        assertEquals(vehicleDefectReportJpaEntity.isHasBrokenWheel(), vehicleDefectReport.isHasBrokenWheel());
        assertEquals(vehicleDefectReportJpaEntity.isHasFlatTyre(), vehicleDefectReport.isHasFlatTyre());
        assertEquals(vehicleDefectReportJpaEntity.getUuid(), vehicleDefectReport.getUuid());
        assertEquals(vehicleDefectReportJpaEntity.getPhoto(), vehicleDefectReport.getPhoto());


    }

    @Test
    void domainFromCommandMapping(){
        CreateVehicleDefectReportCommand command = new CreateVehicleDefectReportCommand("test", UUID.randomUUID(), UUID.randomUUID(), true, true, true, true, true,true, photo);


        VehicleDefectReport vehicleDefectReport = VehicleDefectReportMapper.INSTANCE.mapToDomain(command);

        assertEquals(command.description(), vehicleDefectReport.getDescription());
        assertEquals(command.vehicleId().toString(), vehicleDefectReport.getVehicleId().toString());
        assertEquals(command.isUnusable(), vehicleDefectReport.isUnusable());
        assertEquals(command.hasBrokenBrake(), vehicleDefectReport.isHasBrokenBrake());
        assertEquals(command.hasBrokenLight(), vehicleDefectReport.isHasBrokenLight());
        assertEquals(command.hasBrokenWheel(), vehicleDefectReport.isHasBrokenWheel());
        assertEquals(command.hasFlatTyre(), vehicleDefectReport.isHasFlatTyre());
        assertEquals(command.hasOtherDefect(), vehicleDefectReport.isHasOtherDefect());
        assertEquals(command.isUnusable(), vehicleDefectReport.isUnusable());
        assertEquals(command.photo(), vehicleDefectReport.getPhoto());

    }

    @Test
    void lombokTest() {
        VehicleDefectReportJpaEntity vehicleDefectReportJpaEntity = new VehicleDefectReportJpaEntity();
        vehicleDefectReportJpaEntity.setDescription("test");

        assertEquals("test", vehicleDefectReportJpaEntity.getDescription());
    }


}

package be.kdg.prog6.maintenance.adapters.mapper;

import be.kdg.prog6.common.events.CustomerVehicleDefectReportCreatedEvent;
import be.kdg.prog6.maintenance.adapters.dto.DefectReportDTO;
import be.kdg.prog6.maintenance.adapters.out.db.model.DefectReportJpaEntity;
import be.kdg.prog6.maintenance.domain.VehicleDefectReport;
import be.kdg.prog6.maintenance.ports.in.CreateVehicleDefectReportCommand;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DefectReportMapper {
    DefectReportMapper INSTANCE = Mappers.getMapper(DefectReportMapper.class);

    @Mapping(target = "uuid", source = "uuid")
    DefectReportJpaEntity mapToJpaEntity(VehicleDefectReport vehicleDefectReport);

    @InheritInverseConfiguration
    VehicleDefectReport mapToDomain(DefectReportJpaEntity defectReportJpaEntity);

    VehicleDefectReport mapToDomain(CreateVehicleDefectReportCommand command);

    @Mapping(target = "customerContextReportId", source="vehicleDefectReportId")
    CreateVehicleDefectReportCommand mapToCommand(CustomerVehicleDefectReportCreatedEvent event);

    @Mapping(target="isUnusable", source="unusable")
    DefectReportDTO mapToDTO(VehicleDefectReport report);

}

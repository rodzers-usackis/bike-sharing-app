package be.kdg.prog6.customer.adapters.mapper;

import be.kdg.prog6.common.events.CustomerVehicleDefectReportCreatedEvent;
import be.kdg.prog6.customer.adapters.dto.VehicleDefectReportDTO;
import be.kdg.prog6.customer.adapters.dto.CreatedVehicleDefectReportDTO;
import be.kdg.prog6.customer.adapters.out.db.model.VehicleDefectReportJpaEntity;
import be.kdg.prog6.customer.domain.VehicleDefectReport;
import be.kdg.prog6.customer.ports.in.CreateVehicleDefectReportCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses= {CustomerMapper.class})
public interface VehicleDefectReportMapper {

    VehicleDefectReportMapper INSTANCE = Mappers.getMapper(VehicleDefectReportMapper.class);

    @Mapping(target="photo", source="photo")
    VehicleDefectReportJpaEntity mapToJpa(VehicleDefectReport vehicleDefectReport);

    VehicleDefectReport mapToDomain(VehicleDefectReportJpaEntity vehicleDefectReportJpaEntity);


    @Mapping(target="unusable", source="isUnusable")
    VehicleDefectReport mapToDomain(CreateVehicleDefectReportCommand command);


    @Mapping(target="isUnusable", source = "unusable")
    CreateVehicleDefectReportCommand mapToCommand(VehicleDefectReportDTO vehicleDefectReportDTO);

    CreatedVehicleDefectReportDTO mapToDTO(VehicleDefectReport vehicleDefectReport);

    @Mapping(target = "customerId", source = "customer.uuid")
    @Mapping(target = "vehicleDefectReportId", source = "uuid")
    CustomerVehicleDefectReportCreatedEvent mapToEvent(VehicleDefectReport vehicleDefectReport);

}

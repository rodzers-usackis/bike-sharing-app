package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.adapters.mapper.VehicleDefectReportMapper;
import be.kdg.prog6.customer.adapters.out.db.model.VehicleDefectReportJpaEntity;
import be.kdg.prog6.customer.domain.VehicleDefectReport;
import be.kdg.prog6.customer.ports.out.CreateVehicleDefectReportPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDefectReportDbAdapter implements CreateVehicleDefectReportPort {

    private final Logger logger = LoggerFactory.getLogger(VehicleDefectReport.class);

    private final VehicleDefectReportJpaRepository vehicleDefectReportRepository;
    private final CustomerJpaRepository customerRepository;


    public VehicleDefectReportDbAdapter(VehicleDefectReportJpaRepository vehicleDefectReportRepository, CustomerJpaRepository customerRepository) {
        this.vehicleDefectReportRepository = vehicleDefectReportRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public VehicleDefectReport createVehicleDefectReport(VehicleDefectReport vehicleDefectReport) {

        logger.debug(String.format("Creating vehicle defect report (id=%s) for customer with id %s, for a vehicle with id %s", vehicleDefectReport.getUuid(),vehicleDefectReport.getCustomer(), vehicleDefectReport.getVehicleId()));

        VehicleDefectReportJpaEntity vehicleDefectReportJpaEntity = VehicleDefectReportMapper.INSTANCE.mapToJpa(vehicleDefectReport);

        //This save is only used to merge the customer entity with the database
        //The customer entity is not saved, because it should be already in the db
        //Should we make sure that it is in the db?
        customerRepository.save(vehicleDefectReportJpaEntity.getCustomer());

        vehicleDefectReportRepository.save(vehicleDefectReportJpaEntity);

        return VehicleDefectReportMapper.INSTANCE.mapToDomain(vehicleDefectReportJpaEntity);

    }
}

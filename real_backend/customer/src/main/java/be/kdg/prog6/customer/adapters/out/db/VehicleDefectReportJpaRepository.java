package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.adapters.out.db.model.VehicleDefectReportJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDefectReportJpaRepository extends JpaRepository<VehicleDefectReportJpaEntity, Long> {

}


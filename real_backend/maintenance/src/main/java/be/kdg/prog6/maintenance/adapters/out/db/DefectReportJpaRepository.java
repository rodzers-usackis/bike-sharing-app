package be.kdg.prog6.maintenance.adapters.out.db;

import be.kdg.prog6.maintenance.adapters.out.db.model.DefectReportJpaEntity;
import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DefectReportJpaRepository extends JpaRepository<DefectReportJpaEntity, UUID> {
    Optional<List<DefectReportJpaEntity>> findByStatus(VehicleDefectReportStatus status);
}

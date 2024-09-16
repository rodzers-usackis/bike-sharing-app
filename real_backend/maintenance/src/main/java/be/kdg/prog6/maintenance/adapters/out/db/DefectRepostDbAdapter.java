package be.kdg.prog6.maintenance.adapters.out.db;

import be.kdg.prog6.maintenance.adapters.mapper.DefectReportMapper;
import be.kdg.prog6.maintenance.adapters.out.db.model.DefectReportJpaEntity;
import be.kdg.prog6.maintenance.domain.VehicleDefectReport;
import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;
import be.kdg.prog6.maintenance.ports.out.VehicleDefectReportCreatePort;
import be.kdg.prog6.maintenance.ports.out.VehicleDefectReportLoadPort;
import be.kdg.prog6.maintenance.ports.out.VehicleDefectReportUpdatePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class DefectRepostDbAdapter implements VehicleDefectReportCreatePort, VehicleDefectReportLoadPort, VehicleDefectReportUpdatePort {

    private final DefectReportJpaRepository defectReportJpaRepository;

    public DefectRepostDbAdapter(DefectReportJpaRepository defectReportJpaRepository) {
        this.defectReportJpaRepository = defectReportJpaRepository;
    }

    @Override
    public VehicleDefectReport createVehicleDefectReport(VehicleDefectReport vehicleDefectReport) {
        DefectReportJpaEntity defectReportJpaEntity = DefectReportMapper.INSTANCE.mapToJpaEntity(vehicleDefectReport);
        defectReportJpaEntity = defectReportJpaRepository.save(defectReportJpaEntity);
        return DefectReportMapper.INSTANCE.mapToDomain(defectReportJpaEntity);
    }

    @Override
    public Optional<List<VehicleDefectReport>> loadAllVehicleDefectReports() {
        return Optional.of(defectReportJpaRepository.findAll().stream().map(DefectReportMapper.INSTANCE::mapToDomain).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<VehicleDefectReport>> loadOpenVehicleDefectReports() {
        Optional<List<DefectReportJpaEntity>> defectReportJpaEntities = defectReportJpaRepository.findByStatus(VehicleDefectReportStatus.OPEN);
        return defectReportJpaEntities.map(reportJpaEntities -> reportJpaEntities.stream().map(DefectReportMapper.INSTANCE::mapToDomain).collect(Collectors.toList()));
    }

    @Override
    public void updateVehicleDefectReport(VehicleDefectReport vehicleDefectReport) {
        DefectReportJpaEntity defectReportJpaEntity = DefectReportMapper.INSTANCE.mapToJpaEntity(vehicleDefectReport);
        defectReportJpaRepository.save(defectReportJpaEntity);
    }

    @Override
    public Optional<VehicleDefectReport> loadVehicleDefectReport(UUID uuid) {
        return defectReportJpaRepository.findById(uuid).map(DefectReportMapper.INSTANCE::mapToDomain);
    }
}

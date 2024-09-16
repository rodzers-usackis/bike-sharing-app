package be.kdg.prog6.maintenance.adapters.in.web;

import be.kdg.prog6.maintenance.adapters.dto.DefectReportDTO;
import be.kdg.prog6.maintenance.adapters.dto.UpdateDefectReportStatusDTO;
import be.kdg.prog6.maintenance.adapters.mapper.DefectReportMapper;
import be.kdg.prog6.maintenance.domain.VehicleDefectReportStatus;
import be.kdg.prog6.maintenance.ports.in.CreateVehicleDefectReportCommand;
import be.kdg.prog6.maintenance.ports.in.UpdateVehicleDefectReportStatusCommand;
import be.kdg.prog6.maintenance.ports.in.UpdateVehicleReportStatusUseCase;
import be.kdg.prog6.maintenance.ports.in.VehicleDefectReportQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class DefectReportRestController {
    private final Logger logger = LoggerFactory.getLogger(DefectReportRestController.class);

    private final VehicleDefectReportQuery vehicleDefectReportQuery;
    private final UpdateVehicleReportStatusUseCase updateVehicleReportStatusUseCase;

    public DefectReportRestController(VehicleDefectReportQuery vehicleDefectReportQuery, UpdateVehicleReportStatusUseCase updateVehicleReportStatusUseCase) {
        this.vehicleDefectReportQuery = vehicleDefectReportQuery;
        this.updateVehicleReportStatusUseCase = updateVehicleReportStatusUseCase;
    }

    @GetMapping("/maintenance/api/vehicle-defect-reports")
    public List<DefectReportDTO> getVehicleDefectReports(@RequestParam(value = "status", required = false) VehicleDefectReportStatus status) {

        if (status == null) {
            return vehicleDefectReportQuery.getAllVehicleDefectReports().stream().map(DefectReportMapper.INSTANCE::mapToDTO).toList();
        }

//        VehicleDefectReportStatus vehicleDefectReportStatus = VehicleDefectReportStatus.valueOf(status);
        if (status == VehicleDefectReportStatus.OPEN) {
            return vehicleDefectReportQuery.getOpenVehicleDefectReports().stream().map(DefectReportMapper.INSTANCE::mapToDTO).toList();
        }

        throw new IllegalArgumentException("Status not supported");

    }

    @PatchMapping("/maintenance/api/vehicle-defect-report/{vehicleDefectReportId}")
    public ResponseEntity<Void> updateVehicleDefectReportStatus(@PathVariable UUID vehicleDefectReportId, @RequestBody UpdateDefectReportStatusDTO updateDefectReportStatusDTO) {
        logger.info("PATCHING "+vehicleDefectReportId);
        UpdateVehicleDefectReportStatusCommand command = new UpdateVehicleDefectReportStatusCommand(vehicleDefectReportId, updateDefectReportStatusDTO.getStatus());
        updateVehicleReportStatusUseCase.updateVehicleReportStatus(command);
        return ResponseEntity.noContent().build();
    }





}

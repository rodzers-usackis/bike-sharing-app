package be.kdg.prog6.customer.adapters.in.web;

import be.kdg.prog6.customer.adapters.dto.CreatedVehicleDefectReportDTO;
import be.kdg.prog6.customer.adapters.dto.VehicleDefectReportDTO;
import be.kdg.prog6.customer.adapters.mapper.VehicleDefectReportMapper;
import be.kdg.prog6.customer.domain.VehicleDefectReport;
import be.kdg.prog6.customer.ports.in.CreateVehicleDefectReportUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class VehicleDefectReportRestController {
    private final Logger logger = LoggerFactory.getLogger(VehicleDefectReportRestController.class);


    private final CreateVehicleDefectReportUseCase createVehicleDefectReportUseCase;
    private final VehicleDefectReportMapper vehicleDefectReportMapper = VehicleDefectReportMapper.INSTANCE;

    public VehicleDefectReportRestController(CreateVehicleDefectReportUseCase createVehicleDefectReportUseCase) {
        this.createVehicleDefectReportUseCase = createVehicleDefectReportUseCase;
    }

    @PostMapping("/api/vehicle-defect-report")
    public ResponseEntity<CreatedVehicleDefectReportDTO> createVehicleDefectReport(@RequestBody VehicleDefectReportDTO vehicleDefectReportDTO) {
        logger.debug("Creating vehicle defect report.");
        VehicleDefectReport createdReport = createVehicleDefectReportUseCase.createVehicleDefectReport(vehicleDefectReportMapper.mapToCommand(vehicleDefectReportDTO));
        CreatedVehicleDefectReportDTO createdReportDTO = vehicleDefectReportMapper.mapToDTO(createdReport);
        return new ResponseEntity<>(createdReportDTO, HttpStatus.CREATED);
    }


}

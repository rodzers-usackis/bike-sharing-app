package be.kdg.prog6.station.adapters.in.web;

import be.kdg.prog6.station.domain.Address;
import be.kdg.prog6.station.domain.Location;
import be.kdg.prog6.station.domain.Station;
import be.kdg.prog6.station.ports.in.CreateStationCommand;
import be.kdg.prog6.station.ports.in.CreateStationUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DockController {
    private final CreateStationUseCase createStationUseCase;

    public DockController(CreateStationUseCase createStationUseCase) {
        this.createStationUseCase = createStationUseCase;
    }

    @PostMapping("/api/station/docks/{state}")
    public void createDock(){

    }
}

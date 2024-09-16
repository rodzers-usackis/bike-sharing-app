package be.kdg.prog6.vehicle.adapters.in.web;

import be.kdg.prog6.vehicle.domain.Bike;
import be.kdg.prog6.vehicle.domain.Vehicle;
import be.kdg.prog6.vehicle.domain.VehicleState;
import be.kdg.prog6.vehicle.ports.in.BikeQuery;
import be.kdg.prog6.vehicle.ports.in.CreateBikeCommand;
import be.kdg.prog6.vehicle.ports.in.CreateBikeUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BikeController {

    private final Logger logger = LoggerFactory.getLogger(BikeController.class);

    private final CreateBikeUseCase createBikeUseCase;
    private final BikeQuery bikeQuery;

    public BikeController(CreateBikeUseCase createBikeUseCase, BikeQuery bikeQuery) {
        this.createBikeUseCase = createBikeUseCase;
        this.bikeQuery = bikeQuery;
    }

    // TODO: identifier? station?
    // Identifier currently gets generated automatically
    @PostMapping("/api/bikes/create/{actCost}/{pricePerMin}")
    public void createVehicle(@PathVariable float actCost, @PathVariable float pricePerMin){
        createBikeUseCase.createBike(new CreateBikeCommand(new Vehicle.VehicleUUID(UUID.randomUUID()), actCost, pricePerMin, VehicleState.AVAILABLE));
    }

    @GetMapping("/api/bikes")
    public List<Bike> getAllBikes() {
        return bikeQuery.findAllBikes().orElse(null);
    }
}

package be.kdg.prog6.vehicle.core;

import be.kdg.prog6.common.events.RideActivityEvent;
import be.kdg.prog6.vehicle.domain.Bike;
import be.kdg.prog6.vehicle.ports.in.ChangeVehicleStateUseCase;
import be.kdg.prog6.vehicle.ports.out.BikeLoadPort;
import be.kdg.prog6.vehicle.ports.out.BikeUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultChangeVehicleStateUseCase implements ChangeVehicleStateUseCase {
    private final Logger logger = LoggerFactory.getLogger(DefaultChangeVehicleStateUseCase.class);

    private BikeUpdatePort bikeUpdatePort;

    private BikeLoadPort bikeLoadPort;

    public DefaultChangeVehicleStateUseCase(BikeUpdatePort bikeUpdatePort, BikeLoadPort bikeLoadPort) {
        this.bikeUpdatePort = bikeUpdatePort;
        this.bikeLoadPort = bikeLoadPort;
    }

    @Override
    public void changeVehicleState(RideActivityEvent event) {
        logger.info("Updating bike state with uuid: " + event.vehicleUUID());

        Optional<Bike> optionalBike = bikeLoadPort.loadBike(event.vehicleUUID());
        Bike bike;
        if (optionalBike.isEmpty()){
            throw new RuntimeException(">>> Bike not found");
        } else {
            bike = optionalBike.get();
        }

        if (event.action().equals("PUT_IN")){
            if (bike.isAvailable()){
                throw new RuntimeException(">>> Bike is already available");
            } else {
                bike.park();
            }
        } else if (event.action().equals("TAKE_OUT")){
            if (bike.isBeingUsed()){
                throw new RuntimeException(">>> Bike is already being used");
            } else {
                bike.ride();
            }
            bike.ride();
        }

        bikeUpdatePort.updateBikeState(bike);
    }
}

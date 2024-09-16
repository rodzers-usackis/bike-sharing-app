package be.kdg.prog6.vehicle.ports.in;

import be.kdg.prog6.common.events.RideActivityEvent;
import org.springframework.transaction.annotation.Transactional;

public interface ChangeVehicleStateUseCase {
    @Transactional
    void changeVehicleState(RideActivityEvent event);
}

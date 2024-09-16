package be.kdg.prog6.vehicle.ports.in;

import be.kdg.prog6.vehicle.domain.Vehicle;
import be.kdg.prog6.vehicle.domain.VehicleState;

public record CreateBikeCommand(Vehicle.VehicleUUID uuid, float actCost, float pricePerMin, VehicleState vehicleState) {
}

package be.kdg.prog6.vehicle.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Bike extends Vehicle {
    private Price price;

    public Bike(Price price) {
        super();
        this.price = price;
    }

    public Bike(VehicleUUID vehicleUUID, Price price) {
        super();
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public void ride(){
        this.setVehicleState(VehicleState.IN_USE);
    }

    public void park(){
        this.setVehicleState(VehicleState.AVAILABLE);
    }

    public boolean isBeingUsed(){
       return this.getVehicleState().equals(VehicleState.IN_USE);
    }

    public boolean isAvailable(){
        return this.getVehicleState().equals(VehicleState.AVAILABLE);
    }
}

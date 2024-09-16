package be.kdg.prog6.vehicle.domain;

public class Truck extends Vehicle {
    private String model;
    private String licensePlate;

    public Truck(String model, String licensePlate) {
        super();
        this.model = model;
        this.licensePlate = licensePlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}

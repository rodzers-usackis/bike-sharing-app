package be.kdg.prog6.vehicle.domain;

public class Step extends Vehicle {
    private Price price;

    public Step(Price price) {
        super();
        this.price = price;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}

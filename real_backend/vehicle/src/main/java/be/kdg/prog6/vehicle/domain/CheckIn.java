package be.kdg.prog6.vehicle.domain;

public class CheckIn {
    private long id;
    private Price price;

    public CheckIn(long id, Price price) {
        this.id = id;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}

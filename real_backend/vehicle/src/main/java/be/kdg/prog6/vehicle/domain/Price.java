package be.kdg.prog6.vehicle.domain;

public record Price(Money costPerMinute, Money activationCost) {
    @Override
    public Money costPerMinute() {
        return costPerMinute;
    }

    @Override
    public Money activationCost() {
        return activationCost;
    }
}

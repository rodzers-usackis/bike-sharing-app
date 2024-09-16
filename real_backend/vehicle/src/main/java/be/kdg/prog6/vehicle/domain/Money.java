package be.kdg.prog6.vehicle.domain;

import java.util.Currency;

public record Money(float amount) {
    public static Currency CURRENCY = Currency.getInstance("USD");

    @Override
    public float amount() {
        return amount;
    }
}

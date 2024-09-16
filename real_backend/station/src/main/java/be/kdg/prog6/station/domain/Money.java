package be.kdg.prog6.station.domain;

import java.util.Currency;

public record Money(float amount) {
    public static Currency CURRENCY = Currency.getInstance("USD");
}

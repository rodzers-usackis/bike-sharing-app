package be.kdg.prog6.customer.domain;

import lombok.Getter;

import java.util.UUID;

@Getter
public class Subscription {
    private UUID uuid;
    private String name;

    private float activationFee;

    private float pricePerMinute;

    public Subscription(UUID uuid, String name, float activationFee, float pricePerMinute) {
        this.uuid = uuid;
        this.name = name;
        this.activationFee = activationFee;
        this.pricePerMinute = pricePerMinute;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", activationFee=" + activationFee +
                ", pricePerMinute=" + pricePerMinute +
                '}';
    }
}

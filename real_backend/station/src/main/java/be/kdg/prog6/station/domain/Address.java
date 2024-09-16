package be.kdg.prog6.station.domain;

import java.util.UUID;

public record Address(AddressUUID addressUUID, String city, String streetName, int streetNumber) {
    @Override
    public AddressUUID addressUUID() {
        return addressUUID;
    }

    @Override
    public String city() {
        return city;
    }

    @Override
    public String streetName() {
        return streetName;
    }

    @Override
    public int streetNumber() {
        return streetNumber;
    }

    public record AddressUUID(UUID uuid) {
    }
}

package be.kdg.prog6.station.domain;

import java.util.UUID;

public record Location(LocationUUID locationUUID, float longitude, float latitude) {

    @Override
    public LocationUUID locationUUID() {
        return locationUUID;
    }
    @Override
    public float longitude() {
        return longitude;
    }

    @Override
    public float latitude() {
        return latitude;
    }

    public record LocationUUID(UUID uuid) {
    }
}

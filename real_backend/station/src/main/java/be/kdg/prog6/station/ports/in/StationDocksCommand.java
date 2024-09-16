package be.kdg.prog6.station.ports.in;

import be.kdg.prog6.station.domain.DockState;

import java.util.UUID;

public record StationDocksCommand(UUID station, DockState dockState) {
}

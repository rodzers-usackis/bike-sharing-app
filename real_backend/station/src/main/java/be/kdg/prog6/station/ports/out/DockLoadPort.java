package be.kdg.prog6.station.ports.out;

import be.kdg.prog6.station.domain.Dock;

import java.util.Optional;
import java.util.UUID;

public interface DockLoadPort {
    Optional<Dock> loadDock(UUID uuid);
}

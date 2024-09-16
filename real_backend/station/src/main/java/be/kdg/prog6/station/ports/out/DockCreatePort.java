package be.kdg.prog6.station.ports.out;

import be.kdg.prog6.station.domain.Dock;

public interface DockCreatePort {
    void createDock(Dock dock);
}

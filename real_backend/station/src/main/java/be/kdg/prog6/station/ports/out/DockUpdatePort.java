package be.kdg.prog6.station.ports.out;

import be.kdg.prog6.station.domain.Dock;
import org.springframework.stereotype.Component;

public interface DockUpdatePort {
    void updateDock(Dock dock);
}

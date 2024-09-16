package be.kdg.prog6.station.adapters.out.db.dbAdapters;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.DockJpaEntity;
import be.kdg.prog6.station.adapters.out.db.repositories.DockRepository;
import be.kdg.prog6.station.domain.Dock;
import be.kdg.prog6.station.ports.out.DockCreatePort;
import be.kdg.prog6.station.ports.out.DockLoadPort;
import be.kdg.prog6.station.ports.out.DockUpdatePort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class DockDBAdapter implements DockCreatePort, DockLoadPort, DockUpdatePort {

    private final DockRepository dockRepository;

    public DockDBAdapter(DockRepository dockRepository) {
        this.dockRepository = dockRepository;
    }

    @Override
    public void createDock(Dock dock) {
        DockJpaEntity dockJpaEntity = new DockJpaEntity();
        dockJpaEntity.setUuid(dock.dockUUID().uuid());
        dockJpaEntity.setDockState(dock.getDockState());
        dockJpaEntity.setDockNumber(dock.getDockNumber());
        dockRepository.save(dockJpaEntity);
    }

    @Override
    public Optional<Dock> loadDock(UUID dockUUID) {
        Optional<DockJpaEntity> dockJpaEntity = dockRepository.findById(dockUUID);

        if (dockJpaEntity.isEmpty()) return Optional.empty();

        Dock dock = new Dock(new Dock.DockUUID(dockJpaEntity.get().getUuid()), dockJpaEntity.get().getDockNumber() , dockJpaEntity.get().getDockState());
        dock.setVehicleUUID(dockJpaEntity.get().getVehicle());
        return Optional.of(dock);
    }

    @Override
    public void updateDock(Dock dock) {
        DockJpaEntity dockJpaEntity = new DockJpaEntity(dock.dockUUID().uuid());
        dockJpaEntity.setDockNumber(dock.getDockNumber());
        dockJpaEntity.setDockState(dock.getDockState());
        dockJpaEntity.setStation(dock.getStationUUID().uuid());
        dockJpaEntity.setVehicle(dock.getVehicleUUID());
        dockRepository.save(dockJpaEntity);
    }
}

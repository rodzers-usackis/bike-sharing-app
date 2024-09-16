package be.kdg.prog6.station.adapters.out.db.dbAdapters;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.AddressJpaEntity;
import be.kdg.prog6.station.adapters.out.db.jpaEntites.DockJpaEntity;
import be.kdg.prog6.station.adapters.out.db.jpaEntites.LocationJpaEntity;
import be.kdg.prog6.station.adapters.out.db.jpaEntites.StationJpaEntity;
import be.kdg.prog6.station.adapters.out.db.repositories.AddressRepository;
import be.kdg.prog6.station.adapters.out.db.repositories.DockRepository;
import be.kdg.prog6.station.adapters.out.db.repositories.LocationRepository;
import be.kdg.prog6.station.adapters.out.db.repositories.StationRepository;
import be.kdg.prog6.station.domain.Address;
import be.kdg.prog6.station.domain.Dock;
import be.kdg.prog6.station.domain.Location;
import be.kdg.prog6.station.domain.Station;
import be.kdg.prog6.station.ports.out.StationCreatePort;
import be.kdg.prog6.station.ports.out.StationLoadPort;
import be.kdg.prog6.station.ports.out.StationUpdatePort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StationDBAdapter implements StationCreatePort, StationLoadPort, StationUpdatePort {

    private final StationRepository stationRepository;

    private final DockRepository dockRepository;

    private final AddressRepository addressRepository;

    private final LocationRepository locationRepository;

    public StationDBAdapter(StationRepository stationRepository, DockRepository dockRepository, AddressRepository addressRepository, LocationRepository locationRepository) {
        this.stationRepository = stationRepository;
        this.dockRepository = dockRepository;
        this.addressRepository = addressRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public void createStation(Station station) {
        StationJpaEntity stationJpaEntity = new StationJpaEntity();
        stationJpaEntity.setUuid(station.getUuid().uuid());
        stationRepository.save(stationJpaEntity);
    }

    @Override
    public Optional<Station> loadStation(UUID uuid) {
        Optional<StationJpaEntity> stationJpaEntity = stationRepository.findById(uuid);
        if (stationJpaEntity.isEmpty()) return Optional.empty();

        Station station = new Station(new Station.StationUUID(stationJpaEntity.get().getUuid()));
        List<DockJpaEntity> docks = dockRepository.findAllByStationUuid(stationJpaEntity.get().getUuid());

        for (DockJpaEntity dockJpaEntity: docks){
            station.addDockToStation(new Dock(new Dock.DockUUID(dockJpaEntity.getUuid()), dockJpaEntity.getDockNumber(), dockJpaEntity.getDockState()));
        }

        station.setName(stationJpaEntity.get().getName());

        return Optional.of(station);
    }

    @Override
    public List<Station> loadAllStations() {
        // TODO: Performance issues in case of large number of stations and docks
        // Convert StationJpaEntity to Station
        List<StationJpaEntity> stationJpaEntities = stationRepository.findAll();

        List<Station> stations = stationJpaEntities.stream().map(stationJpaEntity -> {
            Station station = new Station(new Station.StationUUID(stationJpaEntity.getUuid()));
            List<DockJpaEntity> docks = dockRepository.findAllByStationUuid(stationJpaEntity.getUuid());

            // TODO: bikes in docks?
            docks.forEach(dockJpaEntity -> station.addDockToStation(new Dock(new Dock.DockUUID(dockJpaEntity.getUuid()), dockJpaEntity.getDockNumber(), dockJpaEntity.getDockState())));

            AddressJpaEntity addressJpaEntity = addressRepository.findByUuid(stationJpaEntity.getAddress());

            Address address = new Address(new Address.AddressUUID(addressJpaEntity.getUuid()), addressJpaEntity.getCity(), addressJpaEntity.getStreetName(), addressJpaEntity.getStreetNumber());

            LocationJpaEntity locationJpaEntity = locationRepository.findByUuid(stationJpaEntity.getLocation());

            Location location = new Location(new Location.LocationUUID(locationJpaEntity.getUuid()), locationJpaEntity.getLatitude(), locationJpaEntity.getLongitude());

            station.setName(stationJpaEntity.getName());
            station.setAddress(address);
            station.setLocation(location);

            return station;
        }).toList();

        return stations;
    }

    @Override
    public void updateStation(Station station) {
        StationJpaEntity stationJpaEntity = new StationJpaEntity(station.getUuid().uuid());
        stationJpaEntity.setAddress(station.getAddress().addressUUID().uuid());
        stationJpaEntity.setLocation(station.getLocation().locationUUID().uuid());
        stationJpaEntity.setName(station.getName());
        stationRepository.save(stationJpaEntity);
    }
}

package be.kdg.prog6.station.adapters.out.db.dbAdapters;

import be.kdg.prog6.station.adapters.out.db.jpaEntites.RideActivityJpaEntity;
import be.kdg.prog6.station.adapters.out.db.mappers.RideActivityMapper;
import be.kdg.prog6.station.adapters.out.db.repositories.RideActivityRepository;
import be.kdg.prog6.station.domain.RideActivity;
import be.kdg.prog6.station.ports.out.RideActivityCreateDBPort;
import be.kdg.prog6.station.ports.out.RideActivityLoadPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class RideActivityDBAdapter implements RideActivityCreateDBPort, RideActivityLoadPort {
    private final RideActivityRepository rideActivityRepository;

    public RideActivityDBAdapter(RideActivityRepository rideActivityRepository) {
        this.rideActivityRepository = rideActivityRepository;
    }

    @Override
    public void createRideActivityDB(RideActivity rideActivity) {
        RideActivityJpaEntity rideActivityJpaEntity = new RideActivityJpaEntity();
        rideActivityJpaEntity.setRideActivityUUID(rideActivity.getRideActivityUUID());
        rideActivityJpaEntity.setRideUUID(rideActivity.getRideUUID());
        rideActivityJpaEntity.setTime(rideActivity.getTime());
        rideActivityJpaEntity.setDockUUID(rideActivity.getDockUUID());
        rideActivityJpaEntity.setStationUUID(rideActivity.getStationUUID());
        rideActivityJpaEntity.setDockAction(rideActivity.getDockAction());
        rideActivityJpaEntity.setCustomerUUID(rideActivity.getCustomerUUID());
        rideActivityJpaEntity.setVehicleUUID(rideActivity.getVehicleUUID());
        rideActivityRepository.save(rideActivityJpaEntity);
    }

    @Override
    public Optional<RideActivity> loadRideActivity(UUID uuid) {
        RideActivityJpaEntity rideActivityJpaEntity = rideActivityRepository.findById(uuid).orElse(null);
        assert rideActivityJpaEntity != null;
        RideActivity rideActivity = new RideActivity(rideActivityJpaEntity.getRideActivityUUID(), rideActivityJpaEntity.getRideUUID(), rideActivityJpaEntity.getVehicleUUID(),
                rideActivityJpaEntity.getCustomerUUID(), rideActivityJpaEntity.getDockAction(), rideActivityJpaEntity.getStationUUID(),
                rideActivityJpaEntity.getDockUUID(), rideActivityJpaEntity.getTime());
        return Optional.of(rideActivity);
    }

    @Override
    public Optional<RideActivity> loadTakeOutBikeRideActivityFromCustomerUUID(UUID customerUUID) {
        RideActivityJpaEntity rideActivityJpaEntity = rideActivityRepository.findTakeOutBikeRideActivityFromCustomerUUID(customerUUID).get(0);
        RideActivity rideActivity = new RideActivity(rideActivityJpaEntity.getRideActivityUUID(), rideActivityJpaEntity.getRideUUID(), rideActivityJpaEntity.getVehicleUUID(),
                rideActivityJpaEntity.getCustomerUUID(), rideActivityJpaEntity.getDockAction(), rideActivityJpaEntity.getStationUUID(),
                rideActivityJpaEntity.getDockUUID(), rideActivityJpaEntity.getTime());
        return Optional.of(rideActivity);
    }
}

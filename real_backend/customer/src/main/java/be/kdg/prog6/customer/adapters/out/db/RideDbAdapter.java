package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.domain.Ride;
import be.kdg.prog6.customer.ports.out.RideCreatePort;
import be.kdg.prog6.customer.ports.out.RideLoadPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RideDbAdapter implements RideCreatePort, RideLoadPort {
    private final RideJpaRepository rideJpaRepository;

    private final RideMapper rideMapper = RideMapper.INSTANCE;

    public RideDbAdapter(RideJpaRepository rideJpaRepository) {
        this.rideJpaRepository = rideJpaRepository;
    }


    @Override
    public Optional<Ride> loadRide(UUID rideUuid) {
        RideJpaEntity rideJpaEntity = rideJpaRepository.findById(rideUuid).orElse(null);
        if (rideJpaEntity == null) {
            return Optional.empty();
        }
        Ride ride = new Ride(rideJpaEntity.getUuid(), rideJpaEntity.getCustomerUUID(), rideJpaEntity.getStartTime(),
                rideJpaEntity.getStartStation(), rideJpaEntity.getEndTime(), rideJpaEntity.getEndStation(),
                rideJpaEntity.getTotalCost(), rideJpaEntity.getActivationFee(), rideJpaEntity.getPricePerMinute());
        return Optional.of(ride);
    }

    @Override
    public Optional<List<Ride>> loadAllRides(UUID customerUUID) {
        List<RideJpaEntity> rideJpaEntities = rideJpaRepository.findAllRidesByCustomerUUID(customerUUID);
        List<Ride> rides = new ArrayList<>();
        for (RideJpaEntity rideJpaEntity : rideJpaEntities) {
            rides.add(new Ride(rideJpaEntity.getUuid(), rideJpaEntity.getCustomerUUID(), rideJpaEntity.getStartTime(),
                    rideJpaEntity.getStartStation(), rideJpaEntity.getEndTime(), rideJpaEntity.getEndStation(),
                    rideJpaEntity.getTotalCost(), rideJpaEntity.getActivationFee(), rideJpaEntity.getPricePerMinute()));
        }
        return Optional.of(rides);
    }

    @Override
    public void createRide(Ride ride) {
        RideJpaEntity rideJpaEntity = new RideJpaEntity();
        rideJpaEntity.setUuid(ride.getUuid());
        rideJpaEntity.setCustomerUUID(ride.getCustomerUUID());
        rideJpaEntity.setStartStation(ride.getStartStation());
        rideJpaEntity.setEndStation(ride.getEndStation());
        rideJpaEntity.setStartTime(ride.getStartTime());
        rideJpaEntity.setEndTime(ride.getEndTime());
        rideJpaEntity.setActivationFee(ride.getActivationFee());
        rideJpaEntity.setPricePerMinute(ride.getPricePerMinute());
        rideJpaEntity.setTotalCost(ride.getTotalCost());
        rideJpaRepository.save(rideJpaEntity);
    }
}

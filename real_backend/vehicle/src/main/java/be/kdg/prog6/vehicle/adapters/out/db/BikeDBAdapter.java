package be.kdg.prog6.vehicle.adapters.out.db;

import be.kdg.prog6.vehicle.domain.Bike;
import be.kdg.prog6.vehicle.domain.Money;
import be.kdg.prog6.vehicle.domain.Price;
import be.kdg.prog6.vehicle.domain.Vehicle;
import be.kdg.prog6.vehicle.ports.out.BikeCreatePort;
import be.kdg.prog6.vehicle.ports.out.BikeLoadPort;
import be.kdg.prog6.vehicle.ports.out.BikeUpdatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class BikeDBAdapter implements BikeCreatePort, BikeLoadPort, BikeUpdatePort {
    private final Logger logger = LoggerFactory.getLogger(BikeDBAdapter.class);

    private final BikeRepository bikeRepository;

    public BikeDBAdapter(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public void createBike(Bike bike) {
        BikeJpaEntity bikeJpaEntity = new BikeJpaEntity();
        bikeJpaEntity.setUuid(bike.getId().uuid());
        bikeJpaEntity.setPrice(bike.getPrice().activationCost().amount());
        bikeJpaEntity.setCostPerMinute(bike.getPrice().costPerMinute().amount());
        bikeJpaEntity.setVehicleState(bike.getVehicleState());
        bikeRepository.save(bikeJpaEntity);
    }

    @Override
    public Optional<Bike> loadBike(UUID uuid) {
        logger.info("Loading bike from database");
        BikeJpaEntity bikeJpaEntity = bikeRepository.findById(uuid).orElse(null);

        if(bikeJpaEntity == null){
            logger.error("Bike not found in db");
            return Optional.empty();
        }
        Bike bike = new Bike(new Price(new Money(bikeJpaEntity.getPrice()), new Money(bikeJpaEntity.getCostPerMinute())));
        bike.setVehicleState(bikeJpaEntity.getVehicleState());
        bike.setId(new Vehicle.VehicleUUID(bikeJpaEntity.getUuid()));

        //TODO: add bike activities; history of the bike's rides

        return Optional.of(bike);
    }

    @Override
    public Optional<List<Bike>> loadAllBikes() {
        List<BikeJpaEntity> bikeJpaEntities = bikeRepository.findAll();
        // Check if list is empty
        // If yes just return an empty optional and exit
        if(bikeJpaEntities.isEmpty()){
            return Optional.empty();
        }

        // Converting BikeJpaEntity to Bike if the list is not empty
        List<Bike> bikes = bikeJpaEntities.stream().map(bikeJpaEntity -> {
                Bike bike = new Bike();
                bike.setId(new Vehicle.VehicleUUID(bikeJpaEntity.getUuid()));
                // Price contains both the initial price and the cost per minute
                bike.setPrice(new Price(new Money(bikeJpaEntity.getPrice()), new Money(bikeJpaEntity.getCostPerMinute())));
                bike.setVehicleState(bikeJpaEntity.getVehicleState());

                return bike;
            }
        ).collect(Collectors.toList());

        return Optional.of(bikes);
    }

    @Override
    public void updateBikeState(Bike bike) {
        BikeJpaEntity bikeJpaEntity = bikeRepository.findById(bike.getId().uuid()).orElse(null);

        if (bikeJpaEntity != null) {
            bikeJpaEntity.setVehicleState(bike.getVehicleState());
            bikeRepository.save(bikeJpaEntity);
        }
    }
}

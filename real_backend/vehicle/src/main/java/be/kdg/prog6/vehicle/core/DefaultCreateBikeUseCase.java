package be.kdg.prog6.vehicle.core;

import be.kdg.prog6.vehicle.domain.Bike;
import be.kdg.prog6.vehicle.domain.Money;
import be.kdg.prog6.vehicle.domain.Price;
import be.kdg.prog6.vehicle.ports.in.CreateBikeCommand;
import be.kdg.prog6.vehicle.ports.in.CreateBikeUseCase;
import be.kdg.prog6.vehicle.ports.out.BikeCreatePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultCreateBikeUseCase implements CreateBikeUseCase {
    private final Logger logger = LoggerFactory.getLogger(DefaultCreateBikeUseCase.class);

    //TODO: We currently use this for testing purposes. A possible addition is identifiers to have a bike start at a station (ex. they expand with more bikes)
    private final BikeCreatePort bikeCreatePort;

    public DefaultCreateBikeUseCase(BikeCreatePort bikeCreatePort) {
        this.bikeCreatePort = bikeCreatePort;
    }

    @Override
    @Transactional
    public void createBike(CreateBikeCommand createBikeCommand) {
        logger.info("Creating bike with uuid: " + createBikeCommand.uuid() + " and price: " + createBikeCommand.actCost() + " and price per minute: " + createBikeCommand.pricePerMin());

        Bike bike = new Bike(createBikeCommand.uuid(), new Price(new Money(createBikeCommand.pricePerMin()), new Money(createBikeCommand.actCost())));
        bike.setVehicleState(createBikeCommand.vehicleState());
        bikeCreatePort.createBike(bike);
    }
}

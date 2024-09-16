package be.kdg.prog6.customer.core;

import be.kdg.prog6.customer.domain.Ride;
import be.kdg.prog6.customer.domain.Subscription;
import be.kdg.prog6.customer.ports.in.CreateRideCommand;
import be.kdg.prog6.customer.ports.in.CreateRideUseCase;
import be.kdg.prog6.customer.ports.out.RideCreatePort;
import be.kdg.prog6.customer.ports.out.SubscriptionLoadPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultCreateRideUseCase implements CreateRideUseCase {
    private final Logger logger = LoggerFactory.getLogger(DefaultCreateRideUseCase.class);

    private final SubscriptionLoadPort subscriptionLoadPort;

    private final RideCreatePort rideCreatePort;

    public DefaultCreateRideUseCase(SubscriptionLoadPort subscriptionLoadPort, RideCreatePort rideCreatePort) {
        this.subscriptionLoadPort = subscriptionLoadPort;
        this.rideCreatePort = rideCreatePort;
    }

    @Override
    public void createRide(CreateRideCommand command) {
        logger.debug(String.format("Creating ride with id %s", command.rideUUID()));
        UUID basicSubscription = UUID.fromString("5380c99f-3596-42a7-af73-9e9eb2e16c61");
        Optional<Subscription> subscription = subscriptionLoadPort.loadSubscription(basicSubscription);

        if (subscription.isPresent()) {
            logger.info(String.format("Subscription %s found", subscription.get().getUuid()));
        } else {
            logger.warn(String.format("Subscription %s not found", basicSubscription));
            return;
        }

        float minutes = ChronoUnit.MINUTES.between(command.startTime(), command.endTime());

        float ppm;
        if (minutes < 1) {
            ppm = subscription.get().getPricePerMinute();
        } else {
            ppm = subscription.get().getPricePerMinute() * minutes;
        }

        float totalPrice = subscription.get().getActivationFee() + ppm + subscription.get().getPricePerMinute();

        logger.debug(String.format("ppm: %s", ppm));
        logger.debug(String.format("Activation fee is: %s | Total Minutes is: %s | Price Per Minute is: %s", subscription.get().getActivationFee(), minutes, subscription.get().getPricePerMinute()));
        logger.debug(String.format("Total price: %s", totalPrice));

        Ride ride = new Ride(command.rideUUID(), command.customerUUID(), command.startTime(), command.startStation(), command.endTime(), command.endStation(), totalPrice, subscription.get().getActivationFee(), subscription.get().getPricePerMinute());

        rideCreatePort.createRide(ride);
    }
}

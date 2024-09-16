package be.kdg.prog6.customer.ports.out;

import be.kdg.prog6.customer.domain.Subscription;

import java.util.Optional;
import java.util.UUID;

public interface SubscriptionLoadPort {
    Optional<Subscription> loadSubscription(UUID subscriptionUUID);
}

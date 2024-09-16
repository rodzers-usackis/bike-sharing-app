package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.domain.Subscription;
import be.kdg.prog6.customer.ports.out.SubscriptionLoadPort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class SubscriptionDbAdapter implements SubscriptionLoadPort {
    private final SubscriptionJpaRepository subscriptionJpaRepository;

    private final SubscriptionMapper subscriptionMapper = SubscriptionMapper.INSTANCE;

    public SubscriptionDbAdapter(SubscriptionJpaRepository subscriptionJpaRepository) {
        this.subscriptionJpaRepository = subscriptionJpaRepository;
    }

    @Override
    public Optional<Subscription> loadSubscription(UUID subscriptionUUID) {
        SubscriptionJpaEntity subscriptionJpaEntity = subscriptionJpaRepository.findById(subscriptionUUID).orElse(null);
        assert subscriptionJpaEntity != null;
        Subscription subscription = new Subscription(subscriptionJpaEntity.getUuid(), subscriptionJpaEntity.getName(),
                subscriptionJpaEntity.getActivationFee(), subscriptionJpaEntity.getPricePerMinute());
        return Optional.of(subscription);
    }
}

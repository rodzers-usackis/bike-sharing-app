package be.kdg.prog6.customer.adapters.out.db;

import be.kdg.prog6.customer.domain.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionJpaEntity mapToJpa(Subscription subscription);

    Subscription mapToDomain(SubscriptionJpaEntity subscriptionJpaEntity);
}

package be.kdg.prog6.customer.adapters.out.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity(name = "customer_subscription")
@Table(name = "customer.subscription", schema = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionJpaEntity {
    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    private String name;

    private float activationFee;

    private float pricePerMinute;
}

package be.kdg.prog6.customer.adapters.out.db.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerJpaEntity {

    @Id
    @Type(type = "uuid-char")
    private UUID uuid;

    private String fullName;
    private String email;
    private String phoneNumber;



}

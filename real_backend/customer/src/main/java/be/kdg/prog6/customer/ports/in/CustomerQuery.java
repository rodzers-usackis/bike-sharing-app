package be.kdg.prog6.customer.ports.in;

import be.kdg.prog6.customer.domain.Customer;

import java.util.UUID;

public interface CustomerQuery {
    Customer getCustomer(UUID customerUUID);
}

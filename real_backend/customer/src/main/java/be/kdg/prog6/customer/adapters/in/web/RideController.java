package be.kdg.prog6.customer.adapters.in.web;

import be.kdg.prog6.customer.domain.Ride;
import be.kdg.prog6.customer.ports.in.RideQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
public class RideController {
    private final Logger logger = LoggerFactory.getLogger(RideController.class);

    private final RideQuery rideQuery;

    public RideController(RideQuery rideQuery) {
        this.rideQuery = rideQuery;
    }

    @GetMapping("/api/customer/rides/{rideUUID}")
    public ResponseEntity<Ride> getRideOverview(@PathVariable UUID rideUUID) {
        logger.info("Getting ride overview for ride with id " + rideUUID);

        Ride ride = rideQuery.getRide(rideUUID);

        if (ride == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ride, HttpStatus.OK);
    }

    @GetMapping("/api/customer/rides/ridehistory/{customerUUID}")
    public ResponseEntity<List<Ride>> getAllRidesOfCustomer(@PathVariable UUID customerUUID) {
        logger.info("Getting all rides of customer with id " + customerUUID);

        List<Ride> rides = rideQuery.getAllRides(customerUUID);

        if (rides.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(rides, HttpStatus.OK);
    }
}

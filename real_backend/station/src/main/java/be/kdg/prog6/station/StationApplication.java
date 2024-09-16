package be.kdg.prog6.station;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class StationApplication {

    public static void main(String[] args) {
        SpringApplication.run(StationApplication.class, args);
    }

}

package com.cab;

import com.cab.entity.CabBooking;
import com.cab.repo.CabBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
public class CabBookingApplication {

    @Autowired
    private CabBookingRepo cabBookingRepository;
    
    @Bean
    public RestTemplate cabBookingRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(CabBookingApplication.class, args);
    }

    // This method will initialize data into the H2 database
    @PostConstruct
    public void initialize() {
        // Create some initial bookings
        CabBooking booking1 = new CabBooking();
        booking1.setSource("London");
        booking1.setDestination("Manchester");
        booking1.setFare(80.0);

        CabBooking booking2 = new CabBooking();
        booking2.setSource("London");
        booking2.setDestination("Birmingham");
        booking2.setFare(100.0);

        CabBooking booking3 = new CabBooking();
        booking3.setSource("Manchester");
        booking3.setDestination("Liverpool");
        booking3.setFare(60.0);

        // Save them to the database
        cabBookingRepository.saveAll(Arrays.asList(booking1, booking2, booking3));
    }
}

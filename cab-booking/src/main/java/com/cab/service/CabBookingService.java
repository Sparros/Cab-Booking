package com.cab.service;

import com.cab.entity.CabBooking;
import com.cab.repo.CabBookingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CabBookingService {

    @Autowired
    private CabBookingRepo cabBookingRepository;

    @Autowired
    @Qualifier("cabBookingRestTemplate")
    private RestTemplate restTemplate; // Inject RestTemplate to call external services

    // URL for the Fare Calculation Microservice
    private static final String FARE_CALCULATION_URL = "http://localhost:8081/fare-calculation/calculate-fare"; 

    public CabBooking bookCab(CabBooking cabBooking) {
        // Calculate the fare by calling the Fare Calculation Microservice
        double fare = calculateFare(cabBooking.getSource(), cabBooking.getDestination());

        // Set the fare for the booking
        cabBooking.setFare(fare);

        // Save the booking to the database
        return cabBookingRepository.save(cabBooking);
    }

    // Method to calculate fare by calling an external microservice
    private double calculateFare(String source, String destination) {
        // Build the URL with the source and destination as query parameters
        String url = FARE_CALCULATION_URL + "?source=" + source + "&destination=" + destination;

        // Call the Fare Calculation Microservice and get the fare (as a Double)
        Double fare = restTemplate.getForObject(url, Double.class);

        // Return the calculated fare
        return fare != null ? fare : 0.0; // Ensure no null fare is returned
    }
}
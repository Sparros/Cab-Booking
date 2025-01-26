package com.cab.service;

import org.springframework.stereotype.Service;

@Service
public class FareCalculationService {

    public double calculateFare(String source, String destination) {
        // Basic fare calculation
    	double fare = 50.0 + (Math.random() * 100);
    	
    	// Round to 2 decimal places
        return Math.round(fare * 100.0) / 100.0; 
    }
}

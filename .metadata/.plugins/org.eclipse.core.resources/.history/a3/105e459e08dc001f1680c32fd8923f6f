package com.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.cab.model.Cab;

@Controller
@RequestMapping("/fare-calculation")
public class FareCalculationController {

    @Autowired
    private RestTemplate restTemplate;

    // Backend microservice URL
    private static final String BACKEND_URL = "http://localhost:8081/fare-calculation/calculate-fare";

    // Show the form
    @GetMapping("/")
    public String showFareCalculationForm(Model model) {
        model.addAttribute("cabBooking", new Cab()); // Add an empty CabBooking object for the form
        return "fareCalculation";
    }

    // Handle form submission
    @PostMapping("/calculate-fare")
    public String calculateFare(@ModelAttribute Cab cab, Model model) {
        // Build the backend URL with query parameters
        String url = BACKEND_URL + "?source=" + cab.getSource() + "&destination=" + cab.getDestination();

        // Call the backend service
        Double fare = restTemplate.getForObject(url, Double.class);

        // Set the fare in the CabBooking object and pass it back to the JSP
        cab.setFare(fare);
        model.addAttribute("cabBooking", cab);

        return "fareCalculation"; // Render the JSP with the updated CabBooking object
    }
}


package com.cab.controller;

import com.cab.model.Cab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Controller
@RequestMapping("/cab-booking")
public class CabBookingController {

    @Autowired
    private RestTemplate restTemplate;

    // The URL of the cab booking microservice
    private static final String CAB_BOOKING_URL = "http://localhost:8082/cab-booking/book-cab";

    // Show the cab booking form
    @GetMapping("/book-cab")
    public String showCabBookingForm(Model model) {
        model.addAttribute("cabBooking", new Cab());
        return "bookCab"; // JSP view for the booking form
    }

    // Handle form submission to book a cab
    @PostMapping("/book-cab")
    public String bookCab(@ModelAttribute Cab cab, Model model) {
        try {
            // Call the backend (cab-booking microservice) to create a booking
            ResponseEntity<Map> response = restTemplate.postForEntity(CAB_BOOKING_URL, cab, Map.class);

            // Extract data from the response
            if (response.getStatusCode().is2xxSuccessful()) {
                Map<String, Object> responseData = response.getBody();
                String message = (String) responseData.get("message");
                Map<String, Object> cabBooking = (Map<String, Object>) responseData.get("cabBooking");

                // Add the booking information to the model (including fare and ID)
                model.addAttribute("message", message);
                model.addAttribute("cabBooking", cabBooking); // You may want to convert this into a `CabBooking` object if necessary
            } else {
                model.addAttribute("message", "Booking failed. Please try again later.");
            }
        } catch (Exception e) {
            model.addAttribute("message", "An error occurred while processing your request.");
        }
        return "bookCabForm"; // Show the same form with success message and booking details
    }
}

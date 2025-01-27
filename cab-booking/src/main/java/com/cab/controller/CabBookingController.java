package com.cab.controller;

import com.cab.entity.CabBooking;
import com.cab.repo.CabBookingRepo;
import com.cab.service.CabBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cab-booking")
public class CabBookingController {

    @Autowired
    private CabBookingService cabBookingService;
    
    @Autowired
    private CabBookingRepo cabBookingRepository;

    // Endpoint to book a cab and return the details as JSON to the frontend
    @PostMapping("/book-cab")
    public ResponseEntity<Map<String, Object>> bookCab(@RequestBody CabBooking cabBooking) {
        // Book the cab using the service
        CabBooking bookedCab = cabBookingService.bookCab(cabBooking);

        // Prepare the response data
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Booking Successful!");
        response.put("cabBooking", bookedCab); // Pass the booked cab details back

        // Return the response as JSON
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/get-booking/{id}")
    public ResponseEntity<CabBooking> getBookingDetails(@PathVariable Long id) {
        Optional<CabBooking> cabBookingOptional = cabBookingRepository.findById(id);
        
        if (cabBookingOptional.isPresent()) {
            return ResponseEntity.ok(cabBookingOptional.get());
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }
}

package com.cab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cab.service.FareCalculationService;

@RestController
@RequestMapping("/fare-calculation")
public class FareCalculationController {
    @Autowired
    private FareCalculationService fareCalculationService;

    @GetMapping("/calculate-fare")
    public double calculateFare(@RequestParam String source, @RequestParam String destination) {
        return fareCalculationService.calculateFare(source, destination);
    }
}

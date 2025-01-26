package com.cab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FrontendController {

    @GetMapping("/")
    public String showBookingPage() {
        return "thymeleaf/index";  
    }
    
    @GetMapping("/book-cab")
    public String bookCabForm() {
    	return "bookCabForm";
    }
    
    @GetMapping("/calculate-fare")
    public String fareCalculation() { 
    	return "fareCalculation";
    }
}

package com.cab;

import com.cab.controller.CabBookingController;
import com.cab.entity.CabBooking;
import com.cab.repo.CabBookingRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CabBookingController2Test {

    private MockMvc mockMvc;

    @Mock
    private CabBookingRepo cabBookingRepo;

    @InjectMocks
    private CabBookingController cabBookingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cabBookingController).build();
    }

    @Test
    public void testGetBookingDetails() throws Exception {
        // Prepare mock data
        CabBooking cabBooking = new CabBooking();
        cabBooking.setId(1L);
        cabBooking.setSource("London");
        cabBooking.setDestination("Manchester");
        cabBooking.setFare(45.5);

        // Mock the repository call
        when(cabBookingRepo.findById(1L)).thenReturn(Optional.of(cabBooking));

        // Perform the GET request
        mockMvc.perform(get("/cab-booking/get-booking/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.source").value("London"))
                .andExpect(jsonPath("$.destination").value("Manchester"))
                .andExpect(jsonPath("$.fare").value(45.5));

        // Verify that the repository was called
        verify(cabBookingRepo, times(1)).findById(1L);
    }
}

package com.cab;

import com.cab.controller.CabBookingController;
import com.cab.entity.CabBooking;
import com.cab.service.CabBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CabBookingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CabBookingService cabBookingService;

    @InjectMocks
    private CabBookingController cabBookingController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cabBookingController).build();
    }

    @Test
    public void testBookCab() throws Exception {
        // Prepare mock data
        CabBooking cabBooking = new CabBooking();
        cabBooking.setSource("London");
        cabBooking.setDestination("Manchester");
        cabBooking.setFare(45.5);
        cabBooking.setId(1L);

        // Mock the service method
        when(cabBookingService.bookCab(any(CabBooking.class))).thenReturn(cabBooking);

        // Perform the POST request
        mockMvc.perform(post("/cab-booking/book-cab")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"source\":\"London\",\"destination\":\"Manchester\"}"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Booking Successful!"))
                .andExpect(model().attribute("cabBooking", cabBooking))
                .andExpect(view().name("bookCabForms"));

        // Verify that the service method was called
        verify(cabBookingService, times(1)).bookCab(any(CabBooking.class));
    }
}

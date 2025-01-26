package com.cab.repo;

import com.cab.entity.CabBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabBookingRepo extends JpaRepository<CabBooking, Long> {
    // You can define custom queries here if necessary (e.g., to find bookings by source/destination)
}

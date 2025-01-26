package com.cab.repo;

import com.cab.entity.CabBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CabBookingRepo extends JpaRepository<CabBooking, Long> {
    
}

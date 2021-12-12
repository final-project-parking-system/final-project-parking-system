package com.example.parkingSystem.Parking;

import com.example.parkingSystem.Parking.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
}

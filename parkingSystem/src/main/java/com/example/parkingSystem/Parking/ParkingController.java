package com.example.parkingSystem.Parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Access;
import java.util.List;

@RestController
@RequestMapping(path="parking")
public class ParkingController {
    private final ParkingService parkingService;
@Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping
    public List<Parking> getAllParking(){
        return parkingService.getAllParking();
    }

    @GetMapping("/{id}")
    public Parking getFloor(@PathVariable String id){
        return parkingService.getParking(id);

    }

    @PostMapping
    public Parking createParking(@RequestBody Parking parking){
        return parkingService.createParking(parking);
    }

    @DeleteMapping("/{id}")
    public void deleteParking(@PathVariable String id){
        parkingService.deleteParking(id);

    }
    @PutMapping
    public Parking updateNumSlotIstake(Parking parking,int num){
        return parkingService.updateNumSlotIstake(parking,num);
    }


}

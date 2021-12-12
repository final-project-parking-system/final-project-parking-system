package com.example.parkingSystem.Spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(path="spot")
public class SpotController {
    private final SpotService SpotService;
    @Autowired
    public SpotController(com.example.parkingSystem.Spot.SpotService spotService) {
        this.SpotService = spotService;
    }

    @GetMapping("/{id}")
    public Spot getAvailableSpot(@PathVariable String id){
        return SpotService.getAvailableSpot(id);
    }

    @PostMapping
    public com.example.parkingSystem.Spot.Spot createSpot(@RequestBody com.example.parkingSystem.Spot.Spot spot){
        return SpotService.createSpot(spot);
    }

    @PutMapping
    public void updateTaking(Spot spot){
        SpotService.updateTaking(spot);
    }
}
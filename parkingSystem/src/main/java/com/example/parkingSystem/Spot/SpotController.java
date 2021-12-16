package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping(path="spot")
public class SpotController {
    private final SpotService SpotService;
    @Autowired
    public SpotController(com.example.parkingSystem.Spot.SpotService spotService) {
        this.SpotService = spotService;
    }

//    @GetMapping("/{spotType}")
//    public Spot getAvailableSpot(@PathVariable String spotType){
//        return SpotService.getAvailableSpot(spotType);
//    }
    @GetMapping("/{id}")
    public Spot getSpot(@PathVariable String id){
        return SpotService.getSpot(id);
    }
//    @GetMapping
//    public boolean TakingSpot(Spot spot){
//        return SpotService.TakingSpot(spot);
//    }
    @PostMapping
    public Spot createSpot(@RequestBody Spot spot){
        return SpotService.createSpot(spot);
    }

    @PutMapping
    public void updateTaking(Spot spot){
        SpotService.updateTaking(spot);
    }
//    @PutMapping("/date/{id}")
//    public Spot extendDate(@PathVariable String id,
//                           @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDay,
//
//                           @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDay){
//        System.out.println("hi");
//        return  SpotService.extendDate(id,startDay,endDay);
//    }
    @GetMapping
    public List<Spot> findAvailableSpot( @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDay,
                                         @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDay){
        System.out.println("hi");
    return SpotService.findAvailableSpot(startDay,endDay);
    }
}
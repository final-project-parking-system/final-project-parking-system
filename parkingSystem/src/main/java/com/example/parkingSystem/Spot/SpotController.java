package com.example.parkingSystem.Spot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path="spot")
public class SpotController {
    private final SpotService SpotService;
    @Autowired
    public SpotController(com.example.parkingSystem.Spot.SpotService spotService) {
        this.SpotService = spotService;
    }

    @GetMapping("/all")
    public List<Spot> getTickets(){
        return SpotService.getSpots();
    }
    @GetMapping("/{id}")
    public Spot getSpot(@PathVariable String id){
        return SpotService.getSpot(id);
    }
    @PostMapping
    public Spot addSpot(@RequestBody Spot spot){
        return SpotService.addSpot(spot);
    }
    @GetMapping("/{startDate}/{emdDate}")
    public List<Spot> findAvailableSpot(@PathVariable String startDate ,@PathVariable String  emdDate) throws ParseException {


        List <Spot> s= SpotService.findAvailableSpot(startDate , emdDate );
       return s;
    }
}
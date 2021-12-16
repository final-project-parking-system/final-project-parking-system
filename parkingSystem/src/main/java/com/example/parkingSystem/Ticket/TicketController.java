package com.example.parkingSystem.Ticket;


import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path= "ticket")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    @GetMapping
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }

    @PostMapping
    public Ticket addTicket(User user,Spot spot){
        return ticketService.addTicket(user,spot);
    }

//    @PostMapping("/registerByCar")
//    public Ticket addTicketInUser(@RequestBody User user){
//        return ticketService.addTicketInUser(user);
//    }
    @GetMapping("/{platNum}")
    public Ticket getTicket(@PathVariable String platNum){
        return ticketService.getTicket(platNum);

    }
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable String id){
        ticketService.deleteTicket(id);

    }

    @PutMapping("/{plate_num}")
    public void updateTicket(@PathVariable String phone_num){
        ticketService.updateTicket(phone_num);
    }
    @GetMapping("/now")
    public Instant nowD(){
        return Instant.now();
    }

    @PutMapping("/date/{user_id}/{spot_id}")
    public Ticket extendDate(@PathVariable String user_id,@PathVariable String spot_id,
                           @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDay,
                           @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDay){
        System.out.println(startDay);
        System.out.println(spot_id);
        System.out.println(user_id);
        return  ticketService.extendDate(user_id,spot_id,startDay,endDay);
    }

}

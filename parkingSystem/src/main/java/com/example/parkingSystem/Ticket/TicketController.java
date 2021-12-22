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
    @GetMapping("/all")
    public List<Ticket> getTickets(){
        return ticketService.getTickets();
    }

    @PostMapping("/{user_id}/{spot_id}/{startDate}/{endDate}")
    public Ticket addTicket(@PathVariable String user_id ,@PathVariable String spot_id ,@PathVariable String startDate ,@PathVariable String endDate ){
        System.out.println("i'm hear ");
        return ticketService.addTicket(user_id,spot_id,startDate,endDate);
    }
    @PutMapping("/{user_id}/{status}/{startDate}/{endDate}")
    public void entryConfirmation(@PathVariable String user_id ,@PathVariable String status ,@PathVariable String startDate ,@PathVariable String endDate ){
         ticketService.entryConfirmation(user_id,status,startDate,endDate);
    }

//    @GetMapping("/{PhoneNum}")
//    public Ticket getTicket(@PahVariable String PhoneNum){
//        return ticketService.getTicket(PhoneNum);
//
//    }
    @GetMapping
    public void deleteBookingAutomatically(){
        System.out.println("hi");
         ticketService.deleteBookingAutomatically();
    }
    @PutMapping("/delete")
    public void deleteTicket(){
        ticketService.deleteTicket();

    }

}

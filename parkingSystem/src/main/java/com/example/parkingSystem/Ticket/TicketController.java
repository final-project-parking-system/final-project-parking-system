package com.example.parkingSystem.Ticket;


import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Ticket addTicket(User user){
        return ticketService.addTicket(user);
    }

    @GetMapping("/{platNum}")
    public Ticket getTicket(@PathVariable String platNum){
        return ticketService.getTicket(platNum);

    }
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable String id){
        ticketService.deleteTicket(id);

    }

    @PutMapping("/{plate_num}")
    public void updateTicket(@PathVariable String plate_num){
        ticketService.updateTicket(plate_num);
    }
}

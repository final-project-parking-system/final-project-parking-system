package com.example.parkingSystem.Ticket;

import com.example.parkingSystem.Parking.ParkingController;
import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.Spot.SpotController;
import com.example.parkingSystem.Spot.SpotRepository;
import com.example.parkingSystem.User.User;
import com.example.parkingSystem.User.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    private final SpotController spotController;
    private final ParkingController parkingController;
    @Autowired

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, SpotRepository spotRepository, SpotController spotController, ParkingController parkingController) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.spotRepository = spotRepository;
        this.spotController = spotController;
        this.parkingController = parkingController;
   }

    public List<Ticket> getTickets(){

        return ticketRepository.findAll();
    }


    public Ticket addTicket(User user){
        Ticket ticket =new Ticket();
        Long userId = user.getId();
        System.out.println(userId);
        userRepository.getById(userId);
        if(user != null){
            ticket.setUser(user);
            ticket.setStartTime(Instant.now());
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public Ticket getTicket(String platNum) {

        return ticketRepository.findTicketByPlatNumber(platNum);
    }

    public void deleteTicket(String id){
        Long ticketId = Long.parseLong(id);
        ticketRepository.deleteById(ticketId);

    }

    public void updateTicket(String plate_num){
            Ticket ticket = ticketRepository.findTicketByPlatNumber(plate_num);
            Instant end = Instant.now();
            ticket.setEndTime(end);
            Duration timeElapsed = Duration.between(ticket.getStartTime(), end);
            double time = timeElapsed.toMinutes()/60.0;
            int f = (int)Math.ceil(time);
            double price = f*10;
            ticket.setPrice(price);
            ticketRepository.save(ticket);
            Long user_id = ticket.getUser().getId();
            User user = userRepository.findById(user_id).orElse(null);
            Long spot_id = user.getSpot().getId();
            Spot spot = spotRepository.findById(spot_id).orElse(null);
            spotController.updateTaking(spot);
            parkingController.updateNumSlotIstake(spot.getParking(),2);
    }


}

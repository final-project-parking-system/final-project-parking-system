package com.example.parkingSystem.Ticket;

import com.example.parkingSystem.Email.EmailSenderService;
import com.example.parkingSystem.Parking.ParkingController;
import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.Spot.SpotController;
import com.example.parkingSystem.Spot.SpotRepository;
import com.example.parkingSystem.User.User;
import com.example.parkingSystem.User.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    private final SpotController spotController;
    private final ParkingController parkingController;
    private final EmailSenderService emailSenderService;

    @Autowired
    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, SpotRepository spotRepository, SpotController spotController, ParkingController parkingController, EmailSenderService emailSenderService) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.spotRepository = spotRepository;
        this.spotController = spotController;
        this.parkingController = parkingController;
        this.emailSenderService = emailSenderService;
    }

    public List<Ticket> getTickets(){

        return ticketRepository.findAll();
    }


    public Ticket addTicket(User user ,Spot spot){
        Ticket ticket =new Ticket();
        Long spot_Id = spot.getId();
        System.out.println(spot_Id);
        spotRepository.getById(spot_Id);
        if(spot != null&&user!=null){
            ticket.setSpot(spot);
            ticket.setUser(user);
            return ticketRepository.save(ticket);
        }
        return null;
    }
//    public Ticket addTicketInUser(User user) {
//        User data = userRepository.findByphone(user.getPhone());
//        // if user not found
////        Spot spot = spotRepository.findUserSpot(data.getId());
//        // if user have more than one spot on this day
//        System.out.println("fffffff");
//        if (spot != null) {
//            Ticket ticket = new Ticket();
//            ticket.setSpot(spot);
//            ticket.setUser(user);
////            ticket.setStartTime(Instant.now());
//            System.out.println("heeere");
//            return ticketRepository.save(ticket);
//        }
//        else return null;
//    }
//        System.out.println(userRepository.findById(user.getId()));
//        Ticket ticket =new Ticket();
//        Long spot_Id = spot.getId();
//        System.out.println(spot_Id);
//        spotRepository.getById(spot_Id);
//        if(spot != null&&user!=null){
//            ticket.setSpot(spot);
//            ticket.setUser(user);
//            ticket.setStartTime(Instant.now());
//            return ticketRepository.save(ticket);

//        return null;
//    }


    public Ticket getTicket(String platNum) {

        return ticketRepository.findTicketByPhone_num(platNum);
    }

    public void deleteTicket(String id){
        Long ticketId = Long.parseLong(id);
        ticketRepository.deleteById(ticketId);

    }

    public void updateTicket(String phone_num){
            Ticket ticket = ticketRepository.findTicketByPhone_num(phone_num);
            Instant end = Instant.now();
//            ticket.setEndTime(end);
//            Duration timeElapsed = Duration.between(ticket.getStartTime(), end);
//            double time = timeElapsed.toMinutes()/60.0;
//            double f = Math.ceil(time);
//            double price = f*10;
//            ticket.setPrice(price);
//            ticketRepository.save(ticket);
            Long user_id = ticket.getUser().getId();
            User user = userRepository.findById(user_id).orElse(null);
            Long spot_id= ticket.getSpot().getId();
            Spot spot = spotRepository.findById(spot_id).orElse(null);
            spotController.updateTaking(spot);
            userRepository.deleteBySpotOnUser(user_id,spot_id);
            System.out.println(user_id+"  "+spot_id);
            emailSenderService.sendSimpleEmail(user.getEmail(),
                "Be careful while driving",
                user.getfName()+" good bye , your ticket price "+ticket.getPrice());

    }

    public Ticket extendDate(String userid,String spotid, Date startDay, Date endDay) {
        System.out.println(startDay);
        Long user_id = Long.parseLong(userid);
        System.out.println("spot inside serv" + spotRepository.findById(2L));
        Long spot_id = Long.parseLong(spotid);
        User user = userRepository.findById(user_id).orElse(null);
        System.out.println(user.toString());
        Spot spot = spotRepository.findById(spot_id).orElse(null);
        System.out.println(spot.toString());

        Ticket ticket = new Ticket();
        System.out.println(1);
        if (ticket.getSpot().getId() == null) {
            System.out.println(2);
            ticket.setStartTime(startDay);
            ticket.setEndTime(endDay);
            ticket.setUser(user);
            ticket.setSpot(spot);
            return ticketRepository.save(ticket);
        }
        if (spot.getId() == ticket.getSpot().getId()) {
            System.out.println(3);
            if ((ticket.getStartTime().before(startDay) && (ticket.getStartTime().before(endDay)))
                    && (ticket.getEndTime().after(startDay) && ticket.getEndTime().after(endDay))) {
                ticket.setUser(user);
                ticket.setSpot(spot);
                ticket.setStartTime(startDay);
                ticket.setEndTime(endDay);
                return ticketRepository.save(ticket);
            } else {
                return null; }
        }else return null ;
    }





}

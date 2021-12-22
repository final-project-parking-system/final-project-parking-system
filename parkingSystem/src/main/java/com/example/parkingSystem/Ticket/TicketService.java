package com.example.parkingSystem.Ticket;

import com.example.parkingSystem.Email.EmailSenderService;
import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.Spot.SpotController;
import com.example.parkingSystem.Spot.SpotRepository;
import com.example.parkingSystem.User.User;
import com.example.parkingSystem.User.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final SpotRepository spotRepository;
    private final SpotController spotController;
    private final EmailSenderService emailSenderService;

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         UserRepository userRepository, SpotRepository spotRepository,
                         SpotController spotController, EmailSenderService emailSenderService) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.spotRepository = spotRepository;
        this.spotController = spotController;
        this.emailSenderService = emailSenderService;
    }

    public List<Ticket> getTickets(){

        return ticketRepository.findAll();
    }

//create ticket with dates and  price
    public Ticket addTicket(String userId , String spotId ,String startDate,String endDate ){
        System.out.println(userId+" "+spotId+" "+startDate+" "+endDate);
        System.out.println(userId);
        Ticket ticket=new Ticket();
        Long spot_id = Long.parseLong(spotId);
        Long user_id = Long.parseLong(userId);
        Date start_day = null,end_day = null;
        int daysdiff = 0;
        SimpleDateFormat format =new SimpleDateFormat ("yyyy-MM-dd");
        User user =userRepository.findById(user_id).orElse(null);
        System.out.println(user_id);
        System.out.println(user.toString());
        Spot spot =spotRepository.findById(spot_id).orElse(null);
            try {
                start_day = format.parse(startDate);
                end_day = format.parse(endDate);
                Long diff = end_day.getTime() - start_day.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
                daysdiff = (int) diffDays*24;
                System.out.println(daysdiff);
               
            } catch (ParseException e) {
                e.printStackTrace();
            }
        ticket.setStartTime(start_day);
        ticket.setEndTime(end_day);
        ticket.setPrice(daysdiff);
        ticket.setStatus("waiting");
        ticket.setSpot(spot);
        ticket.setUser(user);
        System.out.println(ticket);
        emailSenderService.sendSimpleEmail(user.getEmail(),"slot has been booked successfully on date"+ticket.getStartTime(),"Parking teem ");
        return ticketRepository.save(ticket);

    }

//delete Booking Automatically when end of the specified date
    public void deleteBookingAutomatically(){// لم تزبط معنا
        List <Ticket> tickets = ticketRepository.findAll();
        Date currentSqlDate = new Date(System.currentTimeMillis());
        for (Ticket i : tickets){
            System.out.println(i.toString());
            if (i.getStatus().equalsIgnoreCase("waiting") ){
                System.out.println(i.getStatus());
                if (i.getStartTime().before(currentSqlDate)){
                    User user =i.getUser();
                    System.out.println(i.getStartTime().before(currentSqlDate));
                    i.setStatus("cancelled");
                    tickets.remove(i.getSpot());
                    ticketRepository.save(i);
                    emailSenderService.sendSimpleEmail(user.getEmail(),"slot has been cancelled on date"+i.getStartTime(),"Parking teem ");

                }else continue;
            }else continue;


        }
    }
    public void deleteTicket( ){

    }
    //entry Confirmation by QR code
    public void entryConfirmation(String userId , String stastus , String startDate, String endDate){
        Long user_Id = Long.parseLong(userId);
        User user =userRepository.findById(user_Id).orElse(null);
        List <Ticket> tickets = ticketRepository.findAll();
        Date start_day = null,end_day = null;
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");
        try {
            start_day = format.parse(startDate);
            end_day = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Ticket i : tickets){
            System.out.println(i.toString());
            if (i.getUser().getId() == user.getId() && i.getStatus().equalsIgnoreCase(stastus) &&
                    i.getStartTime().getDate()==(start_day).getDate() && i.getEndTime().getDate()==(end_day).getDate()){
                i.setStatus("Enter");
                ticketRepository.save(i);
                emailSenderService.sendSimpleEmail(user.getEmail(),"Vehicle entry has been confirmed"+i.getStartTime(),"Parking teem ");
            }
        }
    }


}

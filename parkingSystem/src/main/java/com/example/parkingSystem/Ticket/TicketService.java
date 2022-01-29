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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        Ticket ticket=new Ticket();
        Long spot_id = Long.parseLong(spotId);
        Long user_id = Long.parseLong(userId);
        LocalDate start_day = LocalDate.parse(startDate);
        LocalDate end_day = LocalDate.parse(endDate);
        int daysdiff = 0;
        User user =userRepository.findById(user_id).orElse(null);
        Spot spot =spotRepository.findById(spot_id).orElse(null);
        Long diff = ChronoUnit.DAYS.between(start_day,end_day );
        daysdiff = (int) (diff*24);
        ticket.setStartTime(start_day);
        ticket.setEndTime(end_day);
        ticket.setPrice(daysdiff);
        ticket.setStatus("waiting");
        ticket.setSpot(spot);
        ticket.setUser(user);
//        emailSenderService.sendSimpleEmail(user.getEmail(),"slot has been booked successfully on date"+ticket.getStartTime(),"Parking teem ");
        return ticketRepository.save(ticket);

    }

//delete Booking Automatically when end of the current date
    public void deleteBookingAutomatically(){//
        List <Ticket> tickets = ticketRepository.findAll();
        LocalDate currentSqlDate = LocalDate.now();
        for (Ticket i : tickets){
            if (i.getStatus().equalsIgnoreCase("waiting") ){
                if (i.getEndTime().isBefore(currentSqlDate)){
                    User user =i.getUser();
                    i.setStatus("cancelled");
                    ticketRepository.save(i);
                    emailSenderService.sendSimpleEmail(user.getEmail(),"slot has been cancelled on date"+i.getStartTime(),"Parking teem ");

                }else continue;
            }else continue;


        }
    }

//    //entry Confirmation by QR code
    public void entryConfirmation(String userId , String stastus , String startDate, String endDate){
        Long user_Id = Long.parseLong(userId);
        User user =userRepository.findById(user_Id).orElse(null);
        List <Ticket> tickets = ticketRepository.findAll();
        LocalDate start_day = LocalDate.parse(startDate);
        LocalDate end_day = LocalDate.parse(endDate);
        for (Ticket i : tickets){
            if (i.getUser().getId() == user.getId() && i.getStatus().equalsIgnoreCase(stastus) &&
                    i.getStartTime()==(start_day) && i.getEndTime()==(end_day)){
                i.setStatus("Enter");
                ticketRepository.save(i);
                emailSenderService.sendSimpleEmail(user.getEmail(),"Vehicle entry has been confirmed"+i.getStartTime(),"Parking teem ");
            }
        }
    }
        // Admin check the car is exit the parking
        public Ticket exitCar(User user) {
        Long phone_num= user.getPhone();
        if (phone_num!=null){
            Ticket ticket =ticketRepository.findTicketByPhone_num(phone_num);
            if (ticket.getStatus().equals("Enter")){
                ticket.setStatus("done");
                return ticketRepository.save(ticket);
            }
        }
        return  null ;
    }


}

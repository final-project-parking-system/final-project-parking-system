package com.example.parkingSystem.User;


import com.example.parkingSystem.Email.EmailSenderService;
import com.example.parkingSystem.Parking.ParkingController;
import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.Spot.SpotRepository;
import com.example.parkingSystem.Spot.SpotController;
import com.example.parkingSystem.Ticket.TicketController;
import com.example.parkingSystem.Ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;
    private final TicketController ticketController;
    private final SpotRepository spotRepository;
    private final SpotController spotController;
    private final ParkingController parkingController;
    private final TicketRepository ticketRepository;

    @Autowired
    public UserService(EmailSenderService emailSenderService, UserRepository userRepository, TicketController ticketController, SpotRepository spotRepository, SpotController spotController, ParkingController parkingController, TicketRepository ticketRepository) {
        this.emailSenderService = emailSenderService;
        this.userRepository = userRepository;
        this.ticketController = ticketController;
        this.spotRepository = spotRepository;
        this.spotController = spotController;
        this.parkingController = parkingController;
        this.ticketRepository = ticketRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }




//    public void registerByAdmin(User user ,String slotType) {
//        Spot spot = spotController.getAvailableSpot(slotType);
//        System.out.println(spot.getId());
//            userRepository.save(user);
//            userRepository.insertSpotinUser(user.getId(),spot.getId());
//            spotController.updateTaking(spot);
//            ticketController.addTicket(user);
//            emailSenderService.sendSimpleEmail(user.getEmail(),"floor name"+spot.getParking().getName(), "you");
//
//    }
    public User registerByUser(User user) {
        System.out.println(user.toString());
            return userRepository.save(user);
         }

     public void selectSpot(String userid,String spotid)   {
         Long user_id = Long.parseLong(userid);
         Long spot_id = Long.parseLong(spotid);
         Spot spot = spotRepository.getById(spot_id);
         User user=userRepository.getById(user_id);
         userRepository.insertSpotinUser(user_id,spot_id);
         spotController.updateTaking(spot);
//         ticketController.addTicket(user,spot);
         emailSenderService.sendSimpleEmail(
                 user.getEmail(),
                 "Parking has been booked successfully ",
                 user.getfName()+" , We parking System team ");

     }


    public User logInUser(User data){
        User user= userRepository.findByEmail(data.getEmail());
        if (user.getPassword().equals(data.getPassword())){
            return user;
        }else
            return null ;
    }

    public void deleteUserSpot(String userid,String spotid) {//تصير الحاله في التكت consel
        Long user_id = Long.parseLong(userid);
        Long spot_id = Long.parseLong(spotid);
        Spot spot = spotRepository.getById(spot_id);
        User user=userRepository.getById(user_id);
         userRepository.deleteBySpotOnUser(user_id,spot_id);
        spotController.updateTaking(spot);
        emailSenderService.sendSimpleEmail(
                user.getEmail(),
                "Parking has been unbooked successfully ",
                user.getfName()+" , We parking System team ");
    }


    public void updateUser(User data) {
        User user =new User();
        user.setCarModel(data.getCarModel());
        user.setCarName(data.getCarName());
        user.setEmail(data.getEmail());
        user.setfName(data.getfName());
        user.setfName(data.getfName());
        user.setPassword(data.getPassword());
        user.setPhone(data.getPhone());
    }
}

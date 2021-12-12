package com.example.parkingSystem.User;


import com.example.parkingSystem.Parking.Parking;
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

    private final UserRepository userRepository;
    private final TicketController ticketController;
    private final SpotRepository spotRepository;
    private final SpotController spotController;
    private final ParkingController parkingController;
    private final TicketRepository ticketRepository;

    @Autowired
    public UserService(UserRepository userRepository, TicketController ticketController, SpotRepository spotRepository, SpotController spotController, ParkingController parkingController, TicketRepository ticketRepository) {
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
//    public User updateTicket(String id) {
//        Long user_id = Long.parseLong(id);
//        return userRepository.findById(user_id).orElse(null);
//    }
    public void sendEmailToAvailable() {
        User user = new User();
        if (user.isWaiting() == true) {
            Parking parking = new Parking();
            if (parking.getNumAllSlot() != parking.getNumSlotIstake()) {
                //send message
            } else {
                //null
            }
        }else System.out.println("no user waiting");
    }
    public void registerByAdmin(User user) {
        Long spotId = user.getSpot().getId();
        Spot spot = spotRepository.getById(spotId);
        spotController.updateTaking(spot);
        parkingController.updateNumSlotIstake(spot.getParking(),1);
        if (spot != null) {
            user.setSpot(spot);
            userRepository.save(user);
            ticketController.addTicket(user);
        }
    }
    public User registerByUser(User user) {
        Long spotId = user.getSpot().getId();
        Spot spot = spotRepository.getById(spotId);
        spotController.updateTaking(spot);
        parkingController.updateNumSlotIstake(spot.getParking(),1);
        if (spot != null) {
            user.setSpot(spot);
            return userRepository.save(user);
        }else
            return null;
    }

    public User logInUser(User data){
        User user= userRepository.findByEmail(data.getEmail());
        if (user.getPassword().equals(data.getPassword())){
            return user;
        }else
            return null ;
    }

//    public void deleteUserSpot(String userSpot) {
//         userRepository.deleteBySpotOnUser(userSpot);
//    }


    public void updateUser(User data) {
        User user =new User();
        user.setCarModel(data.getCarModel());
        user.setCarName(data.getCarName());
        user.setEmail(data.getEmail());
        user.setfName(data.getfName());
        user.setfName(data.getfName());
        user.setPassword(data.getPassword());
        user.setPhone(data.getPhone());
        user.setSpot(data.getSpot());
    }
}

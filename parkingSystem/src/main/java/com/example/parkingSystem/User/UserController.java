package com.example.parkingSystem.User;

import com.example.parkingSystem.Spot.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(path="users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all-user")
    public List<User> getUsers(){return userService.getUsers();}

//    @GetMapping
//    public void sendEmailToAvailable(){
//        userService.sendEmailToAvailable();
//    }

    @GetMapping("/find-user-by-email")
    public User getUserByEmail(@RequestBody String email){
        return userService.getUser(email);
    }

    @PostMapping("/LogIn-user")
    public User logInUser(User data){
        return userService.logInUser(data);
    }

//    @PostMapping("/register-By-Admin/{slotType}")
//    public void registerByAdmin(@RequestBody User user ,@PathVariable String slotType){
//         userService.registerByAdmin(user,slotType);
//    }
    @PostMapping("/register-By-User")
    public User registerByUser(@RequestBody User user){
        return userService.registerByUser(user);
    }
    @PostMapping("/select-spot/{user_id}/{spot_id}")
    public void selectSpot(@PathVariable String user_id ,@PathVariable String spot_id){
        userService.selectSpot(user_id ,spot_id);
    }
    @DeleteMapping("/delete-user-spot/{user_id}/{spot_id}")
    public void deleteUserSpot(@PathVariable String user_id ,@PathVariable String spot_id){
        userService.deleteUserSpot(user_id ,spot_id);
    }
    @PutMapping("/update-info")
    public void updateUser(@RequestBody User user){
         userService.updateUser(user);
    }

}

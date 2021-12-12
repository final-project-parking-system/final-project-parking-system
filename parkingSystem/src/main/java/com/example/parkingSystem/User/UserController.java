package com.example.parkingSystem.User;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/find-user-by-email")
    public User getUserByEmail(@RequestBody String email){
        return userService.getUser(email);
    }
    @PostMapping("/LogIn-user")
    public User logInUser(User data){
        return userService.logInUser(data);
    }
    @PostMapping("/register-By-Admin")
    public void registerByAdmin(@RequestBody User user){
         userService.registerByAdmin(user);
    }
    @PostMapping("/register-By-User")
    public void registerByUser(@RequestBody User user){
        userService.registerByUser(user);
    }
//    @DeleteMapping("/delete-user-spot")
//    public void deleteUserSpot(@RequestBody String spot){
//        userService.deleteUserSpot(spot);
//    }
    @PutMapping("/update-info")
    public void updateUser(@RequestBody User user){
         userService.updateUser(user);
    }

}

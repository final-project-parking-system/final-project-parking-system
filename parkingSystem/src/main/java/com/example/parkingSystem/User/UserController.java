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

    @GetMapping("/all-user")
    public List<User> getUsers(){return userService.getUsers();}

    @GetMapping("/find-user-by-email")
    public User getUser(@RequestBody String email){
        return userService.getUser(email);
    }

    @PostMapping("/LogIn-user")
    public User logInUser(User data){
        return userService.logInUser(data);
    }

    @PostMapping("/register-By-User")
    public User registerByUser(@RequestBody User user){
        return userService.registerByUser(user);
    }

    @PutMapping("/update-info/{id}")
    public User updateUser(@PathVariable String id,@RequestBody User user){
         return userService.updateUser(id,user);
    }

}

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
    public User getUserByEmail(@RequestBody User user){
        return userService.getUserByEmail(user);
    }

    @PostMapping("/register-By-User")
    public User registerByUser(@RequestBody Form form){
        return userService.registerByUser(form);
    }

    @PutMapping("/update-info/{id}")
    public User updateUser(@PathVariable String id,@RequestBody User user){
         return userService.updateUser(id,user);
    }

}
class Form {
    private User user;
    private Long role_id;

    public User getUser() {
        return user;
    }

    public Long getRole_id() {
        return role_id;
    }
}

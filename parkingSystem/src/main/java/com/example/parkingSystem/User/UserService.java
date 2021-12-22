package com.example.parkingSystem.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User registerByUser(User user) {
        System.out.println(user.toString());
            return userRepository.save(user);
         }

    public User logInUser(User data){
        User user= userRepository.findByEmail(data.getEmail());
        if (user.getPassword().equals(data.getPassword())){
            return user;
        }else
            return null ;
    }
    public User updateUser(String id, User data) {//test that
        Long user_id =Long.parseLong(id);
        User user= userRepository.findById(user_id).orElse(null);
        user.setCarModel(data.getCarModel());
        user.setCarName(data.getCarName());
        user.setEmail(data.getEmail());
        user.setfName(data.getfName());
        user.setfName(data.getfName());
        user.setPassword(data.getPassword());
        user.setPhone(data.getPhone());
       return userRepository.save(user);
    }
}

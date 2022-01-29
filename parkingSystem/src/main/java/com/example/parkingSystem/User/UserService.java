package com.example.parkingSystem.User;


import com.example.parkingSystem.Role.Role;
import com.example.parkingSystem.Role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user =userRepository.findByEmail(email);
       if (user==null){
           throw new UsernameNotFoundException("User not found in the database");
       }
        Collection<SimpleGrantedAuthority>authorities =new ArrayList<>();
       user.getRoles().forEach(role -> {
           authorities.add(new SimpleGrantedAuthority(role.getName()));
       });
       return  new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserByEmail(User user) {
        String email=user.getEmail();
        return userRepository.findByEmail(email);
    }

    public User registerByUser(Form form) {
        User user= form.getUser();
        if (userRepository.findByEmail(user.getEmail())==null || userRepository.findByPhone(user.getPhone())==null){
        Long role_id = form.getRole_id();
        Role role = roleRepository.findById(role_id).orElse(null);
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);}
        else return null;
         }

    public User logInUser(User data){
        User user= userRepository.findByEmail(data.getEmail());
        if (user.getPassword().equals(data.getPassword())){
            return user;
        }else
            return null ;
    }
    public User updateUser(String id, User data) {
        Long user_id =Long.parseLong(id);
        User user= userRepository.findById(user_id).orElse(null);
        if (userRepository.findByEmail(user.getEmail())==null || userRepository.findByPhone(user.getPhone())==null){
            user.setCarModel(data.getCarModel());
            user.setCarName(data.getCarName());
            user.setEmail(data.getEmail());
            user.setfName(data.getfName());
            user.setfName(data.getfName());
            user.setPassword(data.getPassword());
            user.setPhone(data.getPhone());
            return userRepository.save(user);}
        else{
            return null;
        }
    }
}

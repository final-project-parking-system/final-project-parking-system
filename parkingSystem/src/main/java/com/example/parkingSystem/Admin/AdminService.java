package com.example.parkingSystem.Admin;


import com.example.parkingSystem.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin logIn(Admin data){

        Admin admin= adminRepository.findByUserName(data.getUsername());
        if (admin.getPassword().equals(data.getPassword())){
            return admin;
        }
        return null;

    }
}

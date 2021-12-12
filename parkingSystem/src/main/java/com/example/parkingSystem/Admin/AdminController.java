package com.example.parkingSystem.Admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "admin")
@CrossOrigin("*")
public class AdminController {
    private final AdminService adminService;

    @Autowired

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping("/login")
    public Admin logInAdmin(Admin data){
        return adminService.logIn(data);
    }

}

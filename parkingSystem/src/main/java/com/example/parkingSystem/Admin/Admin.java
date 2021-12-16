package com.example.parkingSystem.Admin;

import com.example.parkingSystem.User.User;

import javax.persistence.*;

@Entity
@Table(name="admin")
public class Admin{
    @Id
    private long id ;
    private String username ;
    private String password ;

    public Admin() {
    }

    public Admin(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


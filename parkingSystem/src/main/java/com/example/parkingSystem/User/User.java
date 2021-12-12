package com.example.parkingSystem.User;

import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.Ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fName;
    private String lName;
    private String carName;
    private String carModel;
    private String platNumber;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private Long phone ;
    private boolean waiting ;


    @ManyToOne
    @JoinColumn(name="spot_id", nullable=false)
    private Spot spot;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Ticket> tickets = new ArrayList<>();

    public User() {
    }


    public User(Long id, String fName, String lName, String carName, String carModel, String platNumber, String email, String password, Long phone, boolean waiting, Spot spot) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.carName = carName;
        this.carModel = carModel;
        this.platNumber = platNumber;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.waiting = waiting;
        this.spot = spot;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getPlatNumber() {
        return platNumber;
    }

    public void setPlatNumber(String platNumber) {
        this.platNumber = platNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", carName='" + carName + '\'' +
                ", carModel='" + carModel + '\'' +
                ", platNumber='" + platNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                ", waiting=" + waiting +
                ", spot=" + spot +
                '}';
    }
}

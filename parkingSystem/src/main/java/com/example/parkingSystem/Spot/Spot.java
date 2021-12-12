package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Parking.Parking;
import com.example.parkingSystem.Ticket.Ticket;
import com.example.parkingSystem.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spot")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String slot_type;
    private Instant expected_date;
    private boolean taking ;

    @ManyToOne (fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="parking_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Parking parking;

    @OneToMany(mappedBy="spot")
    private List<User> users;


    public Spot() {

    }

    public Spot(Long id, String slot_type, Instant expected_date, boolean taking, Parking parking, List<User> users) {
        this.id = id;
        this.slot_type = slot_type;
        this.expected_date = expected_date;
        this.taking = taking;
        this.parking = parking;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlot_type() {
        return slot_type;
    }

    public void setSlot_type(String slot_type) {
        this.slot_type = slot_type;
    }

    public Instant getExpected_date() {
        return expected_date;
    }

    public void setExpected_date(Instant expected_date) {
        this.expected_date = expected_date;
    }

    public boolean isTaking() {
        return taking;
    }

    public void setTaking(boolean taking) {
        this.taking = taking;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", slot_type='" + slot_type + '\'' +
                ", expected_date=" + expected_date +
                ", taking=" + taking +
                ", parking=" + parking +
                ", users=" + users +
                '}';
    }
}

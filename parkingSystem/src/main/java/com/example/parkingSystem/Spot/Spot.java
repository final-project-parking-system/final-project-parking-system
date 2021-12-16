package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Parking.Parking;
import com.example.parkingSystem.Ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "spot")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slot_type;
    private boolean taking ;

    @ManyToOne (fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name ="parking_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Parking parking;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spot")
    private List<Ticket> tickets;

    public Spot() {

    }

    public Spot(Long id, String slot_type,  boolean taking, Parking parking, List<Ticket> tickets) {
        this.id = id;
        this.slot_type = slot_type;
        this.taking = taking;
        this.parking = parking;
        this.tickets = tickets;
    }

    public Boolean hasTickets(){
        System.out.println("size >>>>"+ this.getId() + ":" +this.tickets.size());
        return this.tickets.size() != 0;
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

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Spot{" +
                "id=" + id +
                ", slot_type='" + slot_type + '\'' +
                ", taking=" + taking +
                '}';
    }
}

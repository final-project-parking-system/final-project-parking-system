package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Ticket.Ticket;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "spot")
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String slot_type;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spot")
    @JsonIgnore
    private List<Ticket> tickets;

    public Spot() {

    }

    public Spot(Long id, String slot_type, List<Ticket> tickets) {
        this.id = id;
        this.slot_type = slot_type;
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
                '}';
    }
}

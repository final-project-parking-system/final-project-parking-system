package com.example.parkingSystem.Ticket;

import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.User.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date startTime;
    private Date endTime;
    private String states;
    private double price;
    private boolean waiting ;

    @ManyToOne (fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private User user ;
    @ManyToOne (fetch = FetchType.LAZY,optional = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Spot spot;

    public Ticket() {
    }

    public Ticket(long id, Date startTime, Date endTime, String states, double price, boolean waiting, User user, Spot spot) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.states = states;
        this.price = price;
        this.waiting = waiting;
        this.user = user;
        this.spot = spot;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", states='" + states + '\'' +
                ", price=" + price +
                ", waiting=" + waiting +
                ", user=" + user +
                ", spot=" + spot +
                '}';
    }
}

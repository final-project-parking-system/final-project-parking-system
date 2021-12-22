package com.example.parkingSystem.Ticket;

import com.example.parkingSystem.Spot.Spot;
import com.example.parkingSystem.User.User;
import com.fasterxml.jackson.annotation.*;
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
    private String status;
    private double price;
    private boolean waiting ;//extra thing ...

    @ManyToOne (fetch = FetchType.EAGER,optional = false)
    @JsonIgnoreProperties("tickets")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private User user;

    @ManyToOne (fetch = FetchType.EAGER,optional = false)
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Spot spot;

    public Ticket() {
    }

    public Ticket(long id, Date startTime, Date endTime, String status, double price, boolean waiting, Spot spot ,User user) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.price = price;
        this.waiting = waiting;
        this.spot = spot;
        this.user = user;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date
    getEndTime() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                ", status='" + status + '\'' +
                ", price=" + price +
                ", waiting=" + waiting +
                ", spot=" + spot +
                ", user=" + user +
                '}';
    }
}

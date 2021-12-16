package com.example.parkingSystem.Parking;

import com.example.parkingSystem.Spot.Spot;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking")
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private  int numAllSlot;//عدد المواقف في البارك
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parking")
    private List<Spot> spots = new ArrayList<>();

    public Parking() {
    }

    public Parking(Long id, String name, int numAllSlot, List<Spot> spots) {
        this.id = id;
        this.name = name;
        this.numAllSlot = numAllSlot;
        this.spots = spots;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumAllSlot() {
        return numAllSlot;
    }

    public void setNumAllSlot(int numAllSlot) {
        this.numAllSlot = numAllSlot;
    }

    public List<Spot> getSpots() {
        return spots;
    }

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numAllSlot=" + numAllSlot +
                ", spots=" + spots +
                '}';
    }
}



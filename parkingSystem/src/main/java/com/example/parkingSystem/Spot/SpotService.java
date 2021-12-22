package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpotService {
    private final SpotRepository spotRepository ;
    @Autowired
    public SpotService(SpotRepository spotRepository){
                       this.spotRepository = spotRepository;
    }

    public Spot getSpot(String id){
        Long spot_id = Long.parseLong(id);
        return spotRepository.findById(spot_id).orElse(null);
    }


    public List <Spot> findAvailableSpot(Ticket ticket){
        Date startDate =ticket.getStartTime();
        Date endDate = ticket.getEndTime();
        List<Spot> allSpots = spotRepository.findAll();
        List<Spot> availableSpots = new ArrayList<>();
        for (Spot s: allSpots){
            if (s.hasTickets()){
                if (checkAvailability(s,startDate,endDate) != null){
                    System.out.println("00000");
                    availableSpots.add(s);
                }
            }
            else{
                availableSpots.add(s);
                System.out.println("11111");
            }
        }
        System.out.println("22222");
        return availableSpots;
    }

    Spot checkAvailability(Spot s ,Date startDate , Date endDate){
        boolean flag = true;
        List <Ticket> tickets= s.getTickets();
        for (Ticket i : tickets){
            if (dateWithinTicketDate(i,startDate ,endDate)){
                flag = false;
                break;
            }
        }
        if(flag)
            return s;
        return null;
    }

    Boolean dateWithinTicketDate(Ticket t, Date startDay , Date endDay){
        return !(t.getStartTime().before(startDay)&&(t.getStartTime().before(endDay))
            || (t.getEndTime().after(startDay) && t.getEndTime().after(endDay)));
    }

    public Spot addSpot(Spot spot) {
        System.out.println(spot.toString());
        return spotRepository.save(spot);
    }

    public List<Spot> getSpots() {
        return spotRepository.findAll();
    }
}

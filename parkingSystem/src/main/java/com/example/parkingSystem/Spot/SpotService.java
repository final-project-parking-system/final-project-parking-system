package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Ticket.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.time.LocalDate;
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


    public List <Spot> findAvailableSpot(String start_day, String end_day) throws ParseException {
        LocalDate startDate = LocalDate.parse(start_day);
        LocalDate endDate = LocalDate.parse(end_day);
        List<Spot> allSpots = spotRepository.findAll();
        for (Spot s: allSpots){
            if (s.hasTickets()){
                if (checkAvailability(s,startDate,endDate) != null){
                    s.setAvailable(true);
                }
                else {
                    s.setAvailable(false);
                }
            }
            else{
                s.setAvailable(true);
            }
        }
        return allSpots;
    }

    Spot checkAvailability(Spot s , LocalDate startDate , LocalDate endDate) throws ParseException {
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

    Boolean dateWithinTicketDate(Ticket t, LocalDate startDay , LocalDate endDay) throws ParseException {
        return !(t.getStartTime().isBefore(startDay)
                && (t.getStartTime().isBefore(endDay))
                || (t.getEndTime().isAfter(startDay)
                && t.getEndTime().isAfter(endDay))
                || t.getStatus().equals("cancelled"));
    }

    public Spot addSpot(Spot spot) {
        return spotRepository.save(spot);
    }

    public List<Spot> getSpots() {
        return spotRepository.findAll();
    }
}

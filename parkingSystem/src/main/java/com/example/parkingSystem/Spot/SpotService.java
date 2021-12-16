package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Parking.Parking;
import com.example.parkingSystem.Parking.ParkingRepository;
import com.example.parkingSystem.Ticket.Ticket;
import com.example.parkingSystem.Ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SpotService {
    private final SpotRepository spotRepository ;
    private final ParkingRepository parkingRepository;
    private final TicketRepository ticketRepository;
    @Autowired
    public SpotService(SpotRepository spotRepository, ParkingRepository parkingRepository, TicketRepository ticketRepository) {
        this.spotRepository = spotRepository;
        this.parkingRepository = parkingRepository;
        this.ticketRepository = ticketRepository;
    }

//    public List<Spot> getSpots(){
//        return spotRepository.findAll();
//    }

    public Spot getSpot(String id){
        Long spot_id = Long.parseLong(id);
        return spotRepository.findById(spot_id).orElse(null);
    }

    public  Spot createSpot(Spot spot){
        Long parkingId = spot.getParking().getId();
        Parking parking = parkingRepository.getById(parkingId);
        if(parking != null ){
            spot.setParking(parking);
            return spotRepository.save(spot);
        }
        return null;
    }

//    public Spot getAvailableSpot(String spotType){
//        System.out.println(spotType);
//        if(spotType.equals("normal")){
//            return spotRepository.findAvailableNormalSpot("normal");
//        }
//        else{
//            return spotRepository.findAvailableNormalSpot("not_normal");
//        }
//    }
//    public boolean TakingSpot(Spot spot){
//        Long parking_id =spot.getParking().getId();
//       int allSpot= parkingRepository.findNumallgSpot(parking_id);
//       int spotTaking =spotRepository.findNumOfTakingSpot(parking_id);
//        System.out.println("spot t "+spotTaking);
//        System.out.println("spot all "+allSpot);
//        if (allSpot>=spotTaking){
//        return false;}
//        else
//            return true;
//    }

    public void updateTaking(Spot spot){
      spot.setTaking(!spot.isTaking());
        System.out.println(spot.isTaking());
        spotRepository.save(spot);
    }

//    public Spot extendDate(String id, Date startDay, Date endDay) {//كل سبوت لها أكثر من تيكيت فمعناته ان الديت تنحط بالتكت
//        Long spot_id = Long.parseLong(id);
//        Spot spot= spotRepository.getById(spot_id);
//        if(spot.getStartTime()==null&&spot.getEndTime()==null){
//            spot.setStartTime(startDay);
//            spot.setEndTime(endDay);
//            return spotRepository.save(spot);}
//        else {
//            if((spot.getStartTime().before(startDay)&&(spot.getStartTime().before(endDay)))
//                    &&(spot.getEndTime().after(startDay)&&spot.getEndTime().after(endDay))){
//                spot.setStartTime(startDay);
//                spot.setEndTime(endDay);
//                return spotRepository.save(spot);}
//            else
//                return null ;
//            }
//
//        }

    public List <Spot> findAvailableSpot(Date startDate,Date endDate){
//        Date startDate =ticket.getStartTime();
//        Date endDate = ticket.getEndTime();
        List<Spot> allSpots = spotRepository.findAll();
        List<Spot> availableSpots = new ArrayList<>();
        for (Spot s: allSpots){
            if (s.hasTickets()){
                if (checkAvailability(s,startDate,endDate) != null){
                    availableSpots.add(s);
                }
            }
            else
                availableSpots.add(s);
        }
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
}

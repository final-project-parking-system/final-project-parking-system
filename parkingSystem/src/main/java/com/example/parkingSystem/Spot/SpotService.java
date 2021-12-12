package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Parking.Parking;
import com.example.parkingSystem.Parking.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpotService {
    private final SpotRepository spotRepository ;
    private  final ParkingRepository parkingRepository;

    @Autowired
    public SpotService(SpotRepository spotRepository, ParkingRepository parkingRepository) {
        this.spotRepository = spotRepository;
        this.parkingRepository = parkingRepository;
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

    public Spot getAvailableSpot(String id){
        System.out.println(id);
        if(id.equals("normal")){
            return spotRepository.findAvailableNormalSpot(false,"normal");
        }
        else{
            return spotRepository.findAvailableNormalSpot(false,"not_normal");
        }
    }

    public void updateTaking(Spot spot){
      spot.setTaking(!spot.isTaking());
        spotRepository.save(spot);
    }
}

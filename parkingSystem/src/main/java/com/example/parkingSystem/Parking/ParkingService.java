package com.example.parkingSystem.Parking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingService {
    private final  ParkingRepository parkingRepository;
@Autowired
    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }


    public List<Parking>getAllParking(){
    return parkingRepository.findAll();
    }
    public Parking getParking(String id) {
        Long floor_id = Long.parseLong(id);
        System.out.println(" parkingRepository" + parkingRepository.findById(floor_id).orElse(null));
        return parkingRepository.findById(floor_id).orElse(null);
    }

    public Parking createParking(Parking parking) {
        return parkingRepository.save(parking);

    }

    public void deleteParking(String id) {
        Long parking_id = Long.parseLong(id);
        parkingRepository.deleteById(parking_id);

    }

    public Parking updateNumSlotIstake(Parking parking, int num) {
        if (parking.getNumSlotIstake()!= parking.getNumAllSlot()){
            if (num == 1) {
                parking.setNumSlotIstake(parking.getNumSlotIstake() + 1);
               return parkingRepository.save(parking);
            }else return null;} //send message to withing user
        if (num != 1) {
                parking.setNumSlotIstake(parking.getNumSlotIstake() - 1);
                return parkingRepository.save(parking);
            }else
                return null;
    }

}




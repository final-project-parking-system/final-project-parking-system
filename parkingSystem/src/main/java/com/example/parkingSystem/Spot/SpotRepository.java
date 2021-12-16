package com.example.parkingSystem.Spot;

import com.example.parkingSystem.Ticket.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {

//    @Query(value ="select * from spot where taking = false and slot_type = ?1 limit 1",nativeQuery = true)
//     public Spot findAvailableNormalSpot(String str);
//    @Query (value = "select spot.* from spot ,ticket where  ?1 !between ticket.start_time and ticket.end_time",nativeQuery = true)
//    Spot findByOneDay(Date oneDay);
//    @Query (value = "select spot.* from spot ,ticket where ?1 <= ticket.end_time AND ?2 >= ticket.start_time",nativeQuery = true)
//    Spot findByPeriodDay(Date startDay,Date lastDay);

//    public List<Spot> findAllByTickets(List<Ticket> tickets);
}
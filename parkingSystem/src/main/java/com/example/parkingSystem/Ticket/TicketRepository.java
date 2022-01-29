package com.example.parkingSystem.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    @Query(value ="select ticket.* from ticket join users on users.id = ticket.user_id and users.phone = ?1 ",nativeQuery = true)
    Ticket findTicketByPhone_num(Long phone_num);
}

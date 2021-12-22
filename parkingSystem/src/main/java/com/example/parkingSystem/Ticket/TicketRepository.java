package com.example.parkingSystem.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    @Query(value ="select ticket.* FROM ticket JOIN users_tickets ON ticket.id = users_tickets.tickets_id JOIN users ON users.id = users_tickets.user_id and users.phone=?1",nativeQuery = true)
    Ticket findTicketByPhone_num(Long phone_num);
    @Modifying
    @Query(value ="DELETE FROM ticket WHERE ticket.status=?1",nativeQuery = true)
    @Transactional
    void deleteByStatus(String status);
}

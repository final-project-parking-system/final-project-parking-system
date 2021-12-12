package com.example.parkingSystem.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value ="select * from user where email = ?1 ",nativeQuery = true)
    public User findByEmail(String email);
//    void deleteBySpotOnUser(String userSpot);
}

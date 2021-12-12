package com.example.parkingSystem.Admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query(value ="select * from admin where username = ?1 ",nativeQuery = true)
    public Admin findByUserName(String useName);
}

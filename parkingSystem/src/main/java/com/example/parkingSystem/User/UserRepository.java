package com.example.parkingSystem.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value ="select * from user where email = ?1 ",nativeQuery = true)
    public User findByEmail(String email);
    public User findByphone(Long phone);
@Modifying
    @Query(value ="insert into user_spot (user_id, spot_id )values (:userId,:spotId) ",nativeQuery = true)
@Transactional
     void insertSpotinUser(@Param("userId")Long user_id,@Param("spotId")Long spot_id);

//    @Modifying
//    @Transactional
//    @Query(value = "DELETE FROM user_spot where user_id= ?1 and spot_id = ?2 ; ", nativeQuery = true)
//      void deleteBySpotOnUser(Long user_id,Long spot_id );
}

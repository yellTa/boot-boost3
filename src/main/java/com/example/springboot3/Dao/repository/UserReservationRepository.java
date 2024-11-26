package com.example.springboot3.Dao.repository;

import com.example.springboot3.Dto.UserReservationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserReservationRepository extends JpaRepository<UserReservationDTO, Integer> {

}

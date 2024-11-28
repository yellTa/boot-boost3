package com.example.springboot3.Dao.repository;

import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationInfoRepository extends JpaRepository<ReservationInfoDTO, Integer> {

}

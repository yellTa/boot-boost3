package com.example.springboot3.Dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;

@Repository
public interface ReservationInfoRepository extends JpaRepository<ReservationInfoDTO, Integer> {

}

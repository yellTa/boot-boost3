package com.example.springboot3.Dao.repository;

import com.example.springboot3.Dto.ReservationInfoPriceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationInfoPriceRepository extends JpaRepository<ReservationInfoPriceDTO, Integer> {

}

package org.example.common.repository;

import org.example.common.entity.ForecastEntity;
import org.example.common.entity.ForecastId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ForecastRepository extends JpaRepository<ForecastEntity, ForecastId> {

    List<ForecastEntity> findByIdDate(LocalDate date);
}

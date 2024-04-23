package org.example.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Forecast", uniqueConstraints = @UniqueConstraint(columnNames = {"date", "placeName"}))
public class ForecastEntity {

    @EmbeddedId
    private ForecastId id;

    @Column(name = "day_phenomenon")
    private String dayPhenomenon;

    @Column(name = "night_phenomenon")
    private String nightPhenomenon;

    @Column(name = "temp_min")
    private int tempMin;

    @Column(name = "temp_max")
    private int tempMax;

    @Column(name = "taken_at")
    private LocalDateTime takenAt;
}


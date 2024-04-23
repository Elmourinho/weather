package org.example.importer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForecastDto {

    private LocalDate date;
    private String placeName;
    private String dayPhenomenon;
    private String nightPhenomenon;
    private int tempMin;
    private int tempMax;
    private LocalDateTime takenAt;
}

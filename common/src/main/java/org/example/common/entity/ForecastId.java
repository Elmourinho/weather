package org.example.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ForecastId implements Serializable {
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "place_name")
    private String placeName;
}

package com.project.subwate_backend.domain.station.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Station {

    @Id
    private Long id;
    private Long subwayLineId;
    private String name;
    private double latitude;
    private double longitude;
}

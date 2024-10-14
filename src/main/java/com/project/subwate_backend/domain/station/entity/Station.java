package com.project.subwate_backend.domain.station.entity;

import com.project.subwate_backend.application.station.dto.StationDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Station {

    @Id
    private Long id;
    private Long subwayLineId;
    private String name;
    private double latitude;
    private double longitude;

    public StationDto toStationDto() {
        StationDto stationDto = new StationDto();
        stationDto.setStationId(this.getId());
        stationDto.setStationName(this.getName());
        stationDto.setSubwayLineId(this.getSubwayLineId());
        stationDto.setLatitude(this.getLatitude());
        stationDto.setLongitude(this.getLongitude());
        return stationDto;
    }
}

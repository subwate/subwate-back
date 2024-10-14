package com.project.subwate_backend.application.station.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StationDto {
    @Schema(description = "역 ID", example = "1001000133")
    @NotNull
    private Long stationId;

    @Schema(description = "역 이름", example = "서울")
    @NotNull
    private String stationName;

    @Schema(description = "호선Id", example = "1001")
    private Long subwayLineId;

    @Schema(description = "위도", example = "37.55315")
    private double latitude;

    @Schema(description = "경도", example = "126.972533")
    private double longitude;

    public static StationDto findStationById(Long stationId) {
        StationDto station = new StationDto();
        station.setStationId(stationId);
        station.setStationName("서울");
        station.setSubwayLineId((long) 1001);
        station.setLatitude(37.55315);
        station.setLongitude(126.972533);
        return station;
    }
}
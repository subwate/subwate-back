package com.project.subwate_backend.presentation.subway.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "경로 Dto")
public class RouteDto {

    @Schema(description = "경로 Id")
    @NotNull
    private Long routeId;

    @Schema(description = "출발역")
    @NotNull
    private StationDto departureStation;
    
    @Schema(description = "환승역 List")
    private List<StationDto> transferStationList;

    @Schema(description = "도착역")
    @NotNull
    private StationDto arrivalStation;

    public static RouteDto createRouteByStationId(
            Long departureStationId,
            Long arrivalStationId,
            List<Long> transferStationIdList) {

        StationDto departureStation = StationDto.findStationById(departureStationId);
        StationDto arrivalStation = StationDto.findStationById(arrivalStationId);

        List<StationDto> transferStations = new ArrayList<>();
        for (Long transferStationId : transferStationIdList) {
            StationDto transferStation = StationDto.findStationById(transferStationId);
            transferStations.add(transferStation);
        }

        RouteDto routeDto = new RouteDto();
        routeDto.setDepartureStation(departureStation);
        routeDto.setArrivalStation(arrivalStation);
        routeDto.setTransferStationList(transferStations);

        return routeDto;
    }
}

package com.project.subwate_backend.presentation.subway.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteRequestDto {

    @Schema(description = "시작역Id")
    @NotNull
    public String departureStationId;

    @Schema(description = "환승역Id 리스트")
    public List<String> transitStationIdList = new ArrayList<>();;

    @Schema(description = "도착역Id")
    @NotNull
    public String arrivalStationId;
}

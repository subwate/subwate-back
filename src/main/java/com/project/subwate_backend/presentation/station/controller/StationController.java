package com.project.subwate_backend.presentation.station.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.application.station.service.StationService;
import com.project.subwate_backend.common.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Tag(name = "station")
@RestController
@RequestMapping("/api/v1/station")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StationController {

    StationService stationService;

    @Operation(summary = "위치기반으로 가장 가까운 역 추천")
    @GetMapping("/nearest")
    public ResponseDto<List<StationDto>> getNearestStation(
            @Parameter(description = "위도", example = "37.566535") @RequestParam double latitude,
            @Parameter(description = "경도", example = "126.977969") @RequestParam double longitude) {

        return stationService.getNearestStation(latitude, longitude);
    }

    @Operation(summary = "역 조회")
    @GetMapping("/{stationId}")
    public ResponseDto<StationDto> getStation(
            @Parameter(description = "역Id}") @PathVariable Long stationId) {

        return stationService.getStation(stationId);
    }

}

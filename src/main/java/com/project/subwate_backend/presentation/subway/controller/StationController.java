package com.project.subwate_backend.presentation.subway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.application.station.dto.StationDto;
import com.project.subwate_backend.application.station.service.StationService;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.StationWithSubwayLinesDto;
import com.project.subwate_backend.presentation.subway.mock.SubwayMockDataCreater;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "station")
@RestController
@RequestMapping("/api/v1/station")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @Operation(summary = "위치기반으로 가장 가까운 역 추천")
    @GetMapping("/nearest")
    public ResponseDto<StationWithSubwayLinesDto> getNearestStation(
            @Parameter(description = "위도", example = "37.566535") @RequestParam double latitude,
            @Parameter(description = "경도", example = "126.977969") @RequestParam double longitude) {

        StationWithSubwayLinesDto stationDto = stationService.findNearestStation(latitude, longitude);

        return ResponseDto.of(HttpStatus.OK, "가장 가까운 지하철 역 조회에 성공했습니다.", stationDto);
    }

    @Operation(summary = "역 조회")
    @GetMapping("/{stationId}")
    public ResponseDto<StationDto> getStation(
            @Parameter(description = "역Id}") @PathVariable Long stationId) {

        StationDto station = SubwayMockDataCreater.createMockStation();

        return ResponseDto.of(HttpStatus.OK, "지하철 역 조회에 성공했습니다.", station);
    }
}

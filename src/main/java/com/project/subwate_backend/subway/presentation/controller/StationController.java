package com.project.subwate_backend.subway.presentation.controller;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.subway.presentation.dto.StationDto;
import com.project.subwate_backend.subway.presentation.mock.SubwayMockDataCreater;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "station")
@RestController
@RequestMapping("/api/v1/station")
public class StationController {

    @Operation(summary = "위치기반으로 가장 가까운 역 추천")
    @GetMapping("/nearest")
    public ResponseDto<StationDto> getNearestStation(
            @Parameter(description = "위도", example = "37.566535") @RequestParam double latitude,
            @Parameter(description = "경도", example = "126.977969") @RequestParam double longitude) {

        StationDto station = SubwayMockDataCreater.createMockStation();

        return ResponseDto.of(HttpStatus.OK, "가장 가까운 지하철 역 조회에 성공했습니다.", station);
    }

    @Operation(summary = "역 조회")
    @GetMapping("/{stationId}")
    public ResponseDto<StationDto> getStation(
            @Parameter(description = "역Id}") @PathVariable Long stationId) {

        StationDto station = SubwayMockDataCreater.createMockStation();

        return ResponseDto.of(HttpStatus.OK, "지하철 역 조회에 성공했습니다.", station);
    }
}

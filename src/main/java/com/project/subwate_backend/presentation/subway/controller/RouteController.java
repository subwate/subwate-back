package com.project.subwate_backend.presentation.subway.controller;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.RouteDto;
import com.project.subwate_backend.presentation.subway.dto.request.RouteRequestDto;
import com.project.subwate_backend.presentation.subway.mock.SubwayMockDataCreater;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "subway-route")
@RestController
@RequestMapping("/api/v1/route")
public class RouteController {

    @Operation(summary = "경로 생성")
    @PostMapping("")
    public ResponseDto<RouteDto> createRoute(
            @RequestBody RouteRequestDto requestDto) {

        RouteDto routeDto = SubwayMockDataCreater.createMockRoute();
        return ResponseDto.of(HttpStatus.OK, "지하철 경로 생성에 성공했습니다.", routeDto);
    }

    @Operation(summary = "경로 조회")
    @GetMapping("/{routeId}")
    public ResponseDto<RouteDto> readRoute(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId) {

        RouteDto routeDto = SubwayMockDataCreater.createMockRoute();
        return ResponseDto.of(HttpStatus.OK, "지하철 경로 조회에 성공했습니다.", routeDto);
    }

    @Operation(summary = "모든 경로 조회")
    @GetMapping("")
    public ResponseDto<List<RouteDto>> readAllRoutes() {

        List<RouteDto> routesDto = SubwayMockDataCreater.createMockRouteList();
        return ResponseDto.of(HttpStatus.OK, "모든 지하철 경로 조회에 성공했습니다.", routesDto);
    }

    @Operation(summary = "경로 수정")
    @PutMapping("/{routeId}")
    public ResponseDto<RouteDto> updateRoute(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId,
            @RequestBody RouteRequestDto requestDto) {

        RouteDto routeDto = SubwayMockDataCreater.createMockRoute();
        return ResponseDto.of(HttpStatus.OK, "지하철 경로 수정에 성공했습니다.", routeDto);
    }

    @Operation(summary = "경로 삭제")
    @DeleteMapping("/{routeId}")
    public ResponseDto<Void> deleteRoute(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId) {

        return ResponseDto.of(HttpStatus.OK, "지하철 경로 삭제에 성공했습니다.", null);
    }
}

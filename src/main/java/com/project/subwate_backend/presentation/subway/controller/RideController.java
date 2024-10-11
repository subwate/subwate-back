package com.project.subwate_backend.presentation.subway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.response.StartRideResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "subway-ride")
@RestController
@RequestMapping("api/v1/ride")
public class RideController {

    @Operation(summary = "탑승 시작 설정")
    @PostMapping("/route/{routeId}")
    public ResponseDto<StartRideResponseDto> startRide(
            @Parameter(description = "경로 Id", example = "123") @PathVariable String routeId) {

        StartRideResponseDto responseDto = new StartRideResponseDto();
        responseDto.setExpectedExitTime(600);

        return ResponseDto.of(HttpStatus.OK, "탑승을 시작했습니다.", responseDto);
    }

    @Operation(summary = "탑승 시작 설정 취소")
    @DeleteMapping("/route/{routeId}")
    public ResponseDto<Void> cancelRide(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId) {

        return ResponseDto.of(HttpStatus.OK, "탑승을 취소했습니다.", null);
    }
}

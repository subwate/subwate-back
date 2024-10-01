package com.project.subwate_backend.presentation.subway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.SubwayDto;
import com.project.subwate_backend.presentation.subway.dto.response.GetSubwayByStationResponseDto;
import com.project.subwate_backend.presentation.subway.mock.SubwayMockDataCreater;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "subway")
@RequestMapping("/api/v1/subway")
public class SubwayController {

    @Operation(summary = "탑승하고 있는 지하철 정보 확인")
    @GetMapping("/location")
    public ResponseDto<SubwayDto> getCurrentSubwayLocation() {

        SubwayDto subway = SubwayMockDataCreater.createMockSubway();

        return ResponseDto.of(HttpStatus.OK, "유저가 탑승 중인 지하철의 위치 조회에 성공했습니다.", subway);
    }

    @Operation(summary = "선택한 역의 지하철 조회")
    @GetMapping("/location/station/{stationId}")
    public ResponseDto<GetSubwayByStationResponseDto> getSubwayByStation(
            @Parameter(description = "지하철 역Id", example = "1001000134") @PathVariable String stationId) {

        SubwayDto subway = SubwayMockDataCreater.createMockSubway();

        GetSubwayByStationResponseDto responseDto = new GetSubwayByStationResponseDto();
        responseDto.setUpLineCurrentSubway(subway);
        responseDto.setUpLineNextSubway(subway);
        responseDto.setDownLineCurrentSubway(subway);
        responseDto.setDownLineNextSubway(subway);

        return ResponseDto.of(HttpStatus.OK, "지하철 위치 조회에 성공하였습니다.", responseDto);
    }
}

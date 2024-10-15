package com.project.subwate_backend.presentation.subway.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.application.subway.service.SubwayService;
import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.SubwayDto;
import com.project.subwate_backend.presentation.subway.mock.SubwayMockDataCreater;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "subway")
@RequestMapping("/api/v1/subway")
@RequiredArgsConstructor
public class SubwayController {

    private final SubwayService subwayService;

    @Operation(summary = "탑승하고 있는 지하철 정보 확인")
    @GetMapping("/current")
    public ResponseDto<SubwayDto> getCurrentSubwayLocation() {

        SubwayDto subway = SubwayMockDataCreater.createMockSubway();

        return ResponseDto.of(HttpStatus.OK, "유저가 탑승 중인 지하철의 위치 조회에 성공했습니다.", subway);
    }

    @Operation(summary = "선택한 역의 지하철 조회")
    @GetMapping("/location/station/{stationId}")
    public ResponseDto<List<SubwayDto>> getSubwayByStation(
            @Parameter(description = "지하철 역 Id", example = "1001000133") @PathVariable Long stationId) {

        return subwayService.getSubwayByStation(stationId);
    }

    @Operation(summary = "지하철 호선 조회")
    @GetMapping("/line/{subwayLineId}")
    public ResponseDto<SubwayLineDto> getSubwayLine(@PathVariable Long subwayLineId) {

        SubwayLineDto subwayLine = new SubwayLineDto();
        subwayLine.setId((long) 1001);
        subwayLine.setName("2호선");
        return ResponseDto.of(HttpStatus.OK, "지하철 호선 조회에 성공하였습니다.", subwayLine);
    }

}

package com.project.subwate_backend.presentation.subway.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.application.subwayline.dto.SubwayLineDto;
import com.project.subwate_backend.application.subwayline.service.SubwayLineService;
import com.project.subwate_backend.common.dto.ResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@Tag(name = "subwayLine")
@RequestMapping("/api/v1/line")
@RequiredArgsConstructor
public class SubwayLineController {

    private final SubwayLineService subwayLineService;

    @Operation(summary = "지하철 호선 조회")
    @GetMapping("/{subwayLineId}")
    public ResponseDto<SubwayLineDto> getSubwayLine(@PathVariable Long subwayLineId) {
        return subwayLineService.getSubwayLine(subwayLineId);
    }

    @Operation(summary = "지하철 호선 리스트 조회")
    @GetMapping("/list")
    public ResponseDto<List<SubwayLineDto>> getSubwayLineList(
            @Parameter(description = "호선 Id 리스트", example = "1002,1003,1005") @RequestParam List<Long> subwayLineIdList) {
        return subwayLineService.getSubwayLineList(subwayLineIdList);
    }
}

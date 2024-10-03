package com.project.subwate_backend.presentation.subway.dto.response;

import com.project.subwate_backend.presentation.subway.dto.SubwayDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "지하철 위치 정보 Response Dto")
public class GetSubwayByStationResponseDto {
    @Schema(description = "상행 현재 지하철")
    private SubwayDto upLineCurrentSubway;

    @Schema(description = "상행 다음 지하철")
    private SubwayDto upLineNextSubway;

    @Schema(description = "하행 현재 지하철")
    private SubwayDto downLineCurrentSubway;

    @Schema(description = "하행 다음 지하철")
    private SubwayDto downLineNextSubway;
}

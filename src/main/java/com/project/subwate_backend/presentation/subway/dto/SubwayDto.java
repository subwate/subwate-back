package com.project.subwate_backend.presentation.subway.dto;

import com.project.subwate_backend.presentation.subway.dto.enums.ArrivalStatus;
import com.project.subwate_backend.presentation.subway.dto.enums.SubwayType;
import com.project.subwate_backend.presentation.subway.dto.enums.UpDownClassification;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubwayDto {

    @Schema(description = "지하철 Id", example="4126")
    private String subwayId;

    @Schema(description = "지하철 호선 ID", example = "1001")
    private Long subwayLineId;

    @Schema(description = "상하행선 구분")
    private UpDownClassification upDownClassification;

    @Schema(description = "도착지 방면", example = "광운대행 - 시청방면")
    private String subwayLineName;

    @Schema(description = "현재 지하철 역Id", example = "1001000134")
    private Long currentStationId;

    @Schema(description = "이전 지하철 역 ID", example = "1001000134")
    private Long previousStationId;

    @Schema(description = "다음 지하철 역 ID", example = "1001000132")
    private Long nextStationId;

    @Schema(description = "환승 노선 수", example = "0")
    private int transitRoutesCount;

    @Schema(description = "정보 수신 시간", example = "2024-09-29 10:35:15")
    private String receivedTime;

    @Schema(description = "지하철 도착 상태 구분")
    private ArrivalStatus arrivalStatus;

    @Schema(description = "지하철 타입 구분")
    private SubwayType subwayType;

    @Schema(description = "열차도착예정시간(초)", example = "238")
    private int expectedArrivalTime;
}

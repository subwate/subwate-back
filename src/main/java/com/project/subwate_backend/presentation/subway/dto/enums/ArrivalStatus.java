package com.project.subwate_backend.presentation.subway.dto.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "지하철 도착 상태 구분")
public enum ArrivalStatus {
    @Schema(description = "진입")
    ENTER("0"),

    @Schema(description = "도착")
    ARRIVAL("1"),

    @Schema(description = "출발")
    DEPARTURE("2"),

    @Schema(description = "전역 출발")
    DEPARTURE_PREVIOUS("3"),

    @Schema(description = "전역 진입")
    ENTER_PREVIOUS("4"),

    @Schema(description = "전역 도착")
    ARRIVAL_PREVIOUS("5"),

    @Schema(description = "운행 중")
    IN_OPERATION("99");

    private final String code;

    ArrivalStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static ArrivalStatus fromCode(String code) {
        for (ArrivalStatus status : ArrivalStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid code for ArrivalStatus: " + code);
    }
}
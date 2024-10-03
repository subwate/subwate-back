package com.project.subwate_backend.presentation.subway.dto.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "지하철 타입 구분")
public enum SubwayType {
    @Schema(description = "급행")
    EXPRESS("급행"),

    @Schema(description = "ITX")
    ITX("ITX"),

    @Schema(description = "일반")
    NORMAL("일반"),

    @Schema(description = "특급")
    SUPER_EXPRESS("특급");

    private final String description;

    SubwayType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static SubwayType fromDescription(String description) {
        for (SubwayType type : SubwayType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + description);
    }
}

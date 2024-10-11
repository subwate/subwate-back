package com.project.subwate_backend.presentation.subway.dto.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "지하철 타입 구분")
public enum UpDownClassification {
    
    @Schema(description = "상행")
    UP("상행"),
    
    @Schema(description = "하행")
    DOWN("하행");

    private final String description;

    UpDownClassification(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static UpDownClassification fromDescription(String description) {
        for (UpDownClassification type : UpDownClassification.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown description: " + description);
    }
}

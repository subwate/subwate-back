package com.project.subwate_backend.presentation.subway.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StartRideResponseDto {
    @Schema(description = "도착 남은 시간(초)", example = "300")
    private int expectedExitTime;
}

package com.project.subwate_backend.presentation.subway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {

    @Schema(description = "알림Id", example = "1588")
    @NotNull
    private Long notificationId;

    @Schema(description = "알림 받을 역Id", example = "1001000134")
    @NotNull
    private Long stationId;

    @Schema(description = "알림 활성화 여부", example = "true")
    @NotNull
    private boolean isActive = true;
}

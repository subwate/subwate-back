package com.project.subwate_backend.presentation.subway.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteBookmarkDto {

    @Schema(description = "북마크 경로Id", example = "222")
    @NotNull
    private Long routeBookmarkId;

    @Schema(description = "경로Id", example = "123")
    @NotNull
    private Long routeId;

    @Schema(description = "경로 설정 이름", example = "출근 경로")
    private String routeName;

    @Schema(description = "알림 설정 시간", example = "18:00")
    private String notificationTime;

    @Schema(description = "알림 설정 요일", example = "월,수,금")
    private String notificationDays;

    @Schema(description = "아이콘 url", example = "http://subwate.com/icons.png")
    private String iconUrl;
}

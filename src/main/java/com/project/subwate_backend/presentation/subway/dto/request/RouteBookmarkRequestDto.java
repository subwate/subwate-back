package com.project.subwate_backend.presentation.subway.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteBookmarkRequestDto {
    @Schema(description = "경로 이름", example = "출근 경로")
    private String routeName;
    
    @Schema(description = "알림 설정 시간", example = "18:00")
    private String notificationSetTime; 

    @Schema(description = "알림 설정 요일", example = "월,수,금")
    private String notificationDays; 
    
    @Schema(description = "아이콘 url", example = "http://subwate.com/icons.png")
    private String iconUrl;                   
}

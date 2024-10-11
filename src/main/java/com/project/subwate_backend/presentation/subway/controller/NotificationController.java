package com.project.subwate_backend.presentation.subway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.NotificationDto;
import com.project.subwate_backend.presentation.subway.mock.SubwayMockDataCreater;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "subway-notification")
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Operation(summary = "알림 받을 역 Id로 알림 설정")
    @PostMapping("/station/{stationId}")
    public ResponseDto<NotificationDto> setNotification(
            @Parameter(description = "알림 받을 역Id", example = "1001000132") @PathVariable String stationId) {

        NotificationDto notification = SubwayMockDataCreater.createMockNotification();
        return ResponseDto.of(HttpStatus.OK, "알림 설정에 성공했습니다.", notification);
    }

    @Operation(summary = "알림 취소")
    @DeleteMapping("/station/{stationId}")
    public ResponseDto<Void> cancelNotification(
            @Parameter(description = "알림 취소 할 역Id", example = "1001000132") @PathVariable String stationId) {

        return ResponseDto.of(HttpStatus.OK, "알림 취소에 성공했습니다.", null);
    }

    @Operation(summary = "하차역 도달 전 알림")
    @GetMapping("/push")
    public ResponseDto<Void> pushNotification() {

        return ResponseDto.of(HttpStatus.OK, "하차역 부근입니다.", null);
    }
}

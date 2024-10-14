package com.project.subwate_backend.presentation.friend.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.friend.dto.LocationDto;
import com.project.subwate_backend.presentation.friend.dto.ShareLocationDto;
import com.project.subwate_backend.presentation.subway.dto.SubwayDto;
import com.project.subwate_backend.presentation.subway.dto.enums.ArrivalStatus;
import com.project.subwate_backend.presentation.subway.dto.enums.SubwayType;
import com.project.subwate_backend.presentation.subway.dto.enums.UpDownClassification;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "share-location")
@RestController
@RequestMapping("api/v1/share-location")
public class ShareLocationController {

    @Operation(summary = "위치 공유 요청 보내기")
    @PostMapping("/request")
    public ResponseDto<ShareLocationDto> requestShareLocation(
            @Parameter(description = "친구Id") @RequestBody Long friendId) {

        LocalDateTime expirationTime = LocalDateTime.now().plusDays(1);
        ShareLocationDto shareLocation = new ShareLocationDto(1L, 1004L, friendId, expirationTime, false);
        return ResponseDto.of(HttpStatus.OK, "친구에게 위치 공유 요청에 성공했습니다.", shareLocation);
    }

    @Operation(summary = "들어온 모든 위치 공유 요청 조회")
    @GetMapping("/request")
    public ResponseDto<List<ShareLocationDto>> getReceivedShareLocationRequests() {

        LocalDateTime expirationTime = LocalDateTime.now().plusDays(1);
        ShareLocationDto shareLocation = new ShareLocationDto(1L, 1004L, 7979L, expirationTime, false);
        List<ShareLocationDto> receivedRequests = new ArrayList<>();
        receivedRequests.add(shareLocation);
        receivedRequests.add(shareLocation);
        receivedRequests.add(shareLocation);

        return ResponseDto.of(HttpStatus.OK, "위치 공유 요청 목록을 성공적으로 조회했습니다.", receivedRequests);
    }

    @Operation(summary = "위치 공유 요청 수락")
    @PostMapping("/{shareLocationId}/accept")
    public ResponseDto<ShareLocationDto> acceptShareLocation(
            @Parameter(description = "위치 공유 Id") @PathVariable Long shareLocationId) {

        LocalDateTime expirationTime = LocalDateTime.now().plusDays(1);
        ShareLocationDto shareLocation = new ShareLocationDto(1L, (long) 1004, (long) 7979, expirationTime, true);

        return ResponseDto.of(HttpStatus.OK, "위치 공유 요청이 수락됐습니다.", shareLocation);
    }

    @Operation(summary = "위치 공유 요청 취소")
    @DeleteMapping("/{shareLocationId}/cancel")
    public ResponseDto<Void> cancelShareLocation(
            @Parameter(description = "위치 공유  Id") @PathVariable Long shareLocationId) {

        return ResponseDto.of(HttpStatus.OK, "위치 공유 요청을 취소했습니다.", null);
    }

    @Operation(summary = "위치 공유 요청 거절")
    @DeleteMapping("/{shareLocationId}/reject")
    public ResponseDto<Void> rejectShareLocation(
            @Parameter(description = "위치 공유  Id") @PathVariable Long shareLocationId) {

        return ResponseDto.of(HttpStatus.OK, "위치 공유 요청이 거절됐습니다.", null);
    }

    @Operation(summary = "친구 위치 정보")
    @GetMapping("/{shareLocationId}")
    public ResponseDto<LocationDto> getFriendLocation(
            @Parameter(description = "위치 공유  Id") @PathVariable Long shareLocationId) {

        SubwayDto subway = new SubwayDto();
        subway.setArrivalStatus(ArrivalStatus.ENTER);
        subway.setUpDownClassification(UpDownClassification.UP);
        subway.setSubwayType(SubwayType.NORMAL);
        subway.setCurrentStationId(1000111L);
        subway.setNextStationId(1000112L);
        subway.setPreviousStationId(100033L);
        subway.setReceivedTime("2024-09-29 10:35:15");
        subway.setExpectedArrivalTime(310);
        subway.setTransitRoutesCount(0);
        subway.setSubwayLineName("광운대행 - 시청방면");
        subway.setSubwayLineId(1001L);
        subway.setSubwayId("4126");
        LocationDto location = new LocationDto(1004L, subway);

        return ResponseDto.of(HttpStatus.OK, "친구 위치 조회에 성공했습니다.", location);
    }
}

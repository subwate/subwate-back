package com.project.subwate_backend.presentation.subway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.subway.dto.RouteBookmarkDto;
import com.project.subwate_backend.presentation.subway.dto.request.RouteBookmarkRequestDto;
import com.project.subwate_backend.presentation.subway.mock.SubwayMockDataCreater;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "route-bookmark")
@RequestMapping("/api/v1/bookmark")
public class BookmarkController {

    @Operation(summary = "경로를 즐겨찾기로 설정")
    @PostMapping("/route/{routeId}")
    public ResponseDto<RouteBookmarkDto> createRouteBookmark(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId,
            @RequestBody RouteBookmarkRequestDto addFavoriteRouteRequestDto) {

        RouteBookmarkDto routeBookmark = SubwayMockDataCreater.createMockRouteBookmark();

        return ResponseDto.of(HttpStatus.OK, "경로 즐겨찾기 저장에 성공했습니다.", routeBookmark);
    }

    @Operation(summary = "즐겨찾기 경로를 조회")
    @GetMapping("/route/{routeId}")
    public ResponseDto<RouteBookmarkDto> readRouteBookmark(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId) {

        RouteBookmarkDto routeBookmark = SubwayMockDataCreater.createMockRouteBookmark();

        return ResponseDto.of(HttpStatus.OK, "경로 즐겨찾기 조회에 성공했습니다.", routeBookmark);
    }

    @Operation(summary = "모든 즐겨찾기 경로 조회")
    @GetMapping("/route")
    public ResponseDto<List<RouteBookmarkDto>> readAllFavoriteRoutes() {

        List<RouteBookmarkDto> routeBookmarks = SubwayMockDataCreater.createMockRouteBookmarkList();

        return ResponseDto.of(HttpStatus.OK, "모든 즐겨찾기 경로 조회에 성공했습니다.", routeBookmarks);
    }

    @Operation(summary = "즐겨찾기 설정을 변경")
    @PutMapping("/route/{routeId}")
    public ResponseDto<RouteBookmarkDto> updateRouteBookmark(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId,
            @RequestBody RouteBookmarkRequestDto requestDto) {

        RouteBookmarkDto routeBookmark = SubwayMockDataCreater.createMockRouteBookmark();

        return ResponseDto.of(HttpStatus.OK, "경로 즐겨찾기 저장에 성공했습니다.", routeBookmark);
    }

    @Operation(summary = "경로 즐겨찾기 취소")
    @DeleteMapping("/route/{routeId}")
    public ResponseDto<Void> cancelRouteBookmark(
            @Parameter(description = "경로 ID", example = "123") @PathVariable String routeId) {

        return ResponseDto.of(HttpStatus.OK, "경로 즐겨찾기 취소에 성공했습니다.", null);
    }
}

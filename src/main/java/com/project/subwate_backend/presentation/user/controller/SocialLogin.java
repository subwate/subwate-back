package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/auth")
public class SocialLogin {
    @Operation(summary = "[소셜 로그인] 카카오 로그인", description = "kakao 로그인 시 사용 될 콜백 uri",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = UserInfo.class)),
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "회원가입이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @GetMapping("/kakao/callback")
    public ResponseDto<UserInfo> kakaoLogin(@Parameter(description = "kakao로 받은 access code", required = true) @RequestParam String code) {
        return ResponseDto.of(HttpStatus.OK, "로그인에 성공했습니다.", new UserInfo());
    }

    @Operation(summary = "[소셜 로그인] 구글 로그인", description = "google 로그인 시 사용 될 콜백 uri",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = UserInfo.class)),
                            headers = @Header(name = "Authorization", description = "JWT 토큰", schema = @Schema(type = "string"))),
                    @ApiResponse(responseCode = "401", description = "회원가입이 필요합니다."),
                    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @GetMapping("/google/callback")
    public ResponseDto<UserInfo> googleLogin(@Parameter(description = "google로 받은 access code", required = true) @RequestParam String code) {
        return ResponseDto.of(HttpStatus.OK, "로그인에 성공했습니다.", new UserInfo());
    }

}

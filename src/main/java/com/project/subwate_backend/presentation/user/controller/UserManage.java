package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.application.user.dto.UserInfoDto;
import com.project.subwate_backend.application.user.service.UserManageService;
import com.project.subwate_backend.common.ResponseCode;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.response.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/user")
public class UserManage {

    UserManageService userManageService;

    @Operation(summary = "oauth 정보로 회원가입", description = "Oauth 로그인 후 유저 정보 없는 경우 회원가입",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/join")
    public ResponseDto<UserResponseDto> join(@RequestBody @Validated UserInfoDto userInfoDto) {
        return ResponseDto.of(ResponseCode.USER_JOIN_SUCCESS, userManageService.join(userInfoDto));
    }

    @Operation(summary = "유저 프로필 정보 조회", description = "[JWT 토큰 헤더 필요] 인증이 완료 된 유저의 JWT 토큰 내부의 userId를 통한 유저 정보를 조회",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/me")
    public ResponseDto<UserResponseDto> getUserInfo() {
        return ResponseDto.of(HttpStatus.OK, "유저 정보 조회에 성공했습니다.", new UserResponseDto());
    }

    @Operation(summary = "유저 프로필 이미지 수정", description = "[JWT 토큰 헤더 필요] 인증이 완료 된 유저의 JWT 토큰 내부의 userId를 통한 유저 프로필 이미지 변경",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/me/image")
    public ResponseDto<UserResponseDto> updateUserProfileImage(@Parameter(description = "변경할 유저 프로필 이미지") @RequestParam String image) {

        return ResponseDto.of(HttpStatus.OK, "이미지 변경에 성공하였습니다.", new UserResponseDto());
    }

    @Operation(summary = "유저 프로필 닉네임 수정", description = "[JWT 토큰 헤더 필요] 인증이 완료 된 유저의 JWT 토큰 내부의 userId를 통한 유저 프로필 닉네임 변경",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/me/nickname")
    public ResponseDto<UserResponseDto> updateUserProfileNickname(@Parameter(description = "변경할 유저 프로필 닉네임") @RequestParam String nickname) {
        return ResponseDto.of(HttpStatus.OK, "닉네임 설정에 성공하였습니다.", new UserResponseDto());
    }

}

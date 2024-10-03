package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.UserInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/me")
public class UserManage {

    @Operation(summary = "유저 프로필 정보 조회", description = "[JWT 토큰 헤더 필요] 인증이 완료 된 유저의 JWT 토큰 내부의 userId를 통한 유저 정보를 조회",
            security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("")
    public ResponseDto<UserInfo> getUserInfo() {
        return ResponseDto.of(HttpStatus.OK, "유저 정보 조회에 성공했습니다.", new UserInfo());
    }

    @Operation(summary = "유저 프로필 이미지 수정", description = "[JWT 토큰 헤더 필요] 인증이 완료 된 유저의 JWT 토큰 내부의 userId를 통한 유저 프로필 이미지 변경",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/image")
    public ResponseDto<UserInfo> updateUserProfileImage(@Parameter(description = "변경할 유저 프로필 이미지") @RequestParam String image) {

        return ResponseDto.of(HttpStatus.OK, "이미지 변경에 성공하였습니다.", new UserInfo());
    }

    @Operation(summary = "유저 프로필 닉네임 수정", description = "[JWT 토큰 헤더 필요] 인증이 완료 된 유저의 JWT 토큰 내부의 userId를 통한 유저 프로필 닉네임 변경",
            security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping("/nickname")
    public ResponseDto<UserInfo> updateUserProfileNickname(@Parameter(description = "변경할 유저 프로필 닉네임") @RequestParam String nickname) {
        return ResponseDto.of(HttpStatus.OK, "닉네임 설정에 성공하였습니다.", new UserInfo());
    }

}

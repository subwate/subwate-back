package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.application.service.SocialLoginService;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.response.UserInfoDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SocialLogin {

    SocialLoginService socialLoginService;

    @GetMapping("/kakao/callback")
    public ResponseDto<String> kakaoLogin(@RequestParam String code) {
        return ResponseDto.of(HttpStatus.OK, "로그인에 성공했습니다.", socialLoginService.login(code));
    }

}

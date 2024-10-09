package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.application.user.service.SocialLoginService;
import com.project.subwate_backend.application.user.service.SocialLoginServiceFactory;
import com.project.subwate_backend.common.ResponseCode;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SocialLogin {

    SocialLoginServiceFactory socialLoginServiceFactory;

    @GetMapping("/{provider}/callback")
    public ResponseDto<UserLoginDto> socialLogin(@PathVariable String provider, @RequestParam String code) {
        log.info("socialLogin provider : {}", provider);
        SocialLoginService socialLoginService = socialLoginServiceFactory.getSocialLoginService(provider);

        return ResponseDto.of(ResponseCode.LOGIN_SUCCESS, socialLoginService.login(code));
    }


}

package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.application.user.service.SocialLoginService;
import com.project.subwate_backend.application.user.service.SocialLoginServiceFactory;
import com.project.subwate_backend.common.ResponseCode;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "[소셜 로그인] 소셜 로그인", description = "oauth 로그인 시 사용 될 콜백 uri",
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인에 성공했습니다.",
                            content = @Content(schema = @Schema(implementation = UserLoginDto.class))),
                    @ApiResponse(responseCode = "401", description = "로그인에 실패했습니다."),
                    @ApiResponse(responseCode = "404", description = "회원가입이 필요합니다."),
                    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
            })
    @GetMapping("/{provider}/callback")
    public ResponseDto<UserLoginDto> socialLogin(@PathVariable String provider, @RequestParam String code) {
        log.info("socialLogin provider : {}", provider);
        SocialLoginService socialLoginService = socialLoginServiceFactory.getSocialLoginService(provider);

        return ResponseDto.of(ResponseCode.LOGIN_SUCCESS, socialLoginService.login(code));
    }


}

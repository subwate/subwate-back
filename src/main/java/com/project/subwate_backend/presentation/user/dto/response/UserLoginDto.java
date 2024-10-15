package com.project.subwate_backend.presentation.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "로그인 성공 시 user 정보와 jwt 토큰 응답")
public class UserLoginDto {
    String accessToken;
    String refreshToken;
    String name;
    String email;
    String nickname;
    String image;
    String socialLoginInfo;
    boolean joinStatus;
}
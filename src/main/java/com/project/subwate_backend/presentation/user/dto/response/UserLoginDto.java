package com.project.subwate_backend.presentation.user.dto.response;

import lombok.Data;

@Data
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
package com.project.subwate_backend.presentation.user.dto.response;

import lombok.Data;

@Data
public class UserInfoDto {
    String name;
    String email;
    String nickname;
    String image;
    String socialLoginInfo;
    boolean joinStatus;
}
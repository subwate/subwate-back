package com.project.subwate_backend.presentation.user.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "유저 정보 클래스")
public class UserInfo {
    String name;
    String email;
    String nickname;
    String image;
    @Parameter(description = "소셜 로그인 정보 kakao or google")
    String socialLoginInfo;
    @Parameter(description = "회원 가입 상태")
    boolean joinStatus;
}

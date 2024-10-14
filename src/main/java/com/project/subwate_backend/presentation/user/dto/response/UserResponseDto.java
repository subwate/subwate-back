package com.project.subwate_backend.presentation.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "유저 정보 데이터")
public class UserResponseDto {
    String name;
    String email;
    String nickname;
    String image;
    String socialLoginInfo;
}

package com.project.subwate_backend.application.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserInfoDto {
    @NotBlank(message = "이름 필수 입력 값 입니다.")
    @Pattern(regexp = "^[a-zA-Z가-힣]+$"
            , message = "이름에는 공백이나 특수문자가 포함될 수 없습니다.")
    String name;
    @NotBlank(message = "이메일은 필수 입력 값 입니다.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email;
    @NotBlank(message = "닉네임은 필수 입력 값 입니다.")
    @Pattern(regexp = "(?=\\S+$)[\\w가-힣]{2,10}"
            , message = "닉네임에는 공백이나 특수문자를 사용할 수 없으며, 2자 이상 10자 이하여야 합니다.")
    String nickname;
    @NotBlank(message = "이미지를 선택해주세요")
    String image;
    @NotBlank(message = "가입 인증 한 소셜 oauth를 입력하세요")
    String socialLoginInfo;
}

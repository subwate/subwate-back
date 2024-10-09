package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.application.user.dto.UserInfoDto;
import com.project.subwate_backend.application.user.service.UserManageService;
import com.project.subwate_backend.common.ResponseCode;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.response.UserResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/user")
public class UserManage {

    UserManageService userManageService;

    @PostMapping("/join")
    public ResponseDto<UserResponseDto> join(@RequestBody @Validated UserInfoDto userInfoDto) {
        return ResponseDto.of(ResponseCode.USER_JOIN_SUCCESS, userManageService.join(userInfoDto));
    }

}

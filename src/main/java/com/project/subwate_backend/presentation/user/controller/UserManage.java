package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.application.user.service.UserManageServiceImpl;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.response.UserInfoDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/user")
public class UserManage {

    UserManageServiceImpl userManageService;

    @PostMapping("/join")
    public ResponseDto<UserInfoDto> join(@RequestBody UserInfoDto userInfoDto) {
        return ResponseDto.of(HttpStatus.OK, "회원가입에 성공했습니다.", userManageService.join(userInfoDto));
    }

}

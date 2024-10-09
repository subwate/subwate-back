package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.application.user.dto.UserInfoDto;
import com.project.subwate_backend.presentation.user.dto.response.UserResponseDto;

public interface UserManageService {
    UserResponseDto join(UserInfoDto userInfoDto);
}

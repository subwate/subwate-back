package com.project.subwate_backend.user.application.service;

import com.project.subwate_backend.user.application.dto.UserInfoDto;
import com.project.subwate_backend.user.presentation.dto.response.UserResponseDto;

public interface UserManageService {
    UserResponseDto join(UserInfoDto userInfoDto);
}

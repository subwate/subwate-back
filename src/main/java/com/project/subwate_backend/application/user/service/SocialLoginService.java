package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.presentation.user.dto.response.UserInfoDto;

public interface SocialLoginService {
    UserInfoDto login(String code);
}

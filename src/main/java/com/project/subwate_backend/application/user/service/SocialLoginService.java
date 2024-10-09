package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;

public interface SocialLoginService {
    UserLoginDto login(String code);
}

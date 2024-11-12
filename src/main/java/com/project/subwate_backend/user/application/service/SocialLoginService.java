package com.project.subwate_backend.user.application.service;

import com.project.subwate_backend.user.presentation.dto.response.UserLoginDto;

public interface SocialLoginService {
    UserLoginDto login(String code);
}

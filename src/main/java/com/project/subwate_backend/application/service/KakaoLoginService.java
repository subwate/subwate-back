package com.project.subwate_backend.application.service;

import com.project.subwate_backend.infrastructure.kakao.service.KakaoApiService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KakaoLoginService implements SocialLoginService {

    KakaoApiService kakaoApiService;

    @Override
    public String login(String code) {
        String accessToken = kakaoApiService.getAccessToken(code);

        return "access_token=" + accessToken;
    }
}

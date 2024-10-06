package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.infrastructure.kakao.service.KakaoApiService;
import com.project.subwate_backend.infrastructure.user.repository.UserRepository;
import com.project.subwate_backend.presentation.user.dto.response.UserInfoDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KakaoLoginService implements SocialLoginService {

    KakaoApiService kakaoApiService;
    UserRepository userRepository;

    @Override
    public UserInfoDto login(String code) {
        String accessToken = kakaoApiService.getAccessToken(code);

        UserInfoDto userInfoDto = kakaoApiService.getUserInfo(accessToken);
        if (userInfoDto.getEmail().isBlank() || userInfoDto.getNickname().isBlank()) {
            log.error("Kakao login failed");
        }

        if (userRepository.existsByEmail(userInfoDto.getEmail())) {
            userInfoDto.setSocialLoginInfo("kakao");
            userInfoDto.setJoinStatus(false);
            return userInfoDto;
        }

        return userInfoDto;
    }

}

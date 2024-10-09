package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.application.exception.UnregisteredUserException;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.user.entity.User;
import com.project.subwate_backend.infrastructure.kakao.service.KakaoApiService;
import com.project.subwate_backend.infrastructure.security.JwtTokenProvider;
import com.project.subwate_backend.infrastructure.user.repository.UserRepository;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.project.subwate_backend.common.ResponseCode.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class KakaoLoginService implements SocialLoginService {

    String provider = "kakao";
    KakaoApiService kakaoApiService;
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;
    ResponseMapper responseMapper;

    @Override
    public UserLoginDto login(String code) {

        String accessToken = kakaoApiService.getAccessToken(code);
        UserLoginDto userLoginDto = kakaoApiService.getUserInfo(accessToken);

        User user = userRepository.findByEmail(userLoginDto.getEmail());
        if (user == null) {
            userLoginDto.setSocialLoginInfo(provider);
            userLoginDto.setJoinStatus(false);
            throw new UnregisteredUserException(USER_NOT_FOUND, userLoginDto);
        }


        UserLoginDto responseLoginDto = responseMapper.toUserLoginDto(user);
        createJwtToken(user.getId(), responseLoginDto);

        return responseLoginDto;
    }

    private void createJwtToken(long userId, UserLoginDto userLoginDto) {
        userLoginDto.setAccessToken(jwtTokenProvider.createToken(userLoginDto.getEmail(), userId));
        userLoginDto.setRefreshToken(jwtTokenProvider.createRefreshToken(userLoginDto.getEmail()));
    }

}

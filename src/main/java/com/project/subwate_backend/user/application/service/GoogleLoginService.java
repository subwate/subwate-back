package com.project.subwate_backend.user.application.service;

import com.project.subwate_backend.user.application.exception.UnregisteredUserException;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.user.domain.entity.User;
import com.project.subwate_backend.user.infrastructure.google.service.GoogleApiService;
import com.project.subwate_backend.common.security.JwtTokenProvider;
import com.project.subwate_backend.user.infrastructure.repository.UserRepository;
import com.project.subwate_backend.user.presentation.dto.response.UserLoginDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.project.subwate_backend.user.application.UserResponseCode.USER_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GoogleLoginService implements SocialLoginService {

    String provider = "google";
    GoogleApiService googleApiService;
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;
    ResponseMapper responseMapper;

    @Override
    public UserLoginDto login(String code) {
        String accessToken = googleApiService.getAccessToken(code);
        UserLoginDto userLoginDto = googleApiService.getUserInfo(accessToken);

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

package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.application.exception.UnregisteredUserException;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.user.entity.User;
import com.project.subwate_backend.infrastructure.kakao.service.KakaoApiService;
import com.project.subwate_backend.infrastructure.security.JwtTokenProvider;
import com.project.subwate_backend.infrastructure.user.repository.UserRepository;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DisplayName("카카오 로그인 서비스 테스트")
class KakaoLoginServiceTest {

    private KakaoLoginService kakaoLoginService;

    @MockBean
    private KakaoApiService kakaoApiService;

    @MockBean
    private ResponseMapper responseMapper;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    private UserLoginDto mockUserLoginDto;

    @BeforeEach
    void setUp() {
        kakaoLoginService = new KakaoLoginService(kakaoApiService, userRepository, jwtTokenProvider, responseMapper);

        mockUserLoginDto = new UserLoginDto();
        mockUserLoginDto.setEmail("test@example.com");
        mockUserLoginDto.setName("Test User");
        mockUserLoginDto.setNickname("TestNickname");
        mockUserLoginDto.setSocialLoginInfo("kakao");
    }

    @Test
    @DisplayName("[카카오 로그인]회원가입되지 않은 사용자 카카오 로그인 시 가입되지 않은 사용자 예외 발생")
    void givenUnregisterUser_whenKakaoLoign_thenThrowUnregisteredUserException() {
        //given
        String code = "mockCode";

        when(kakaoApiService.getAccessToken(code)).thenReturn("mockAccessToken");
        when(kakaoApiService.getUserInfo(anyString())).thenReturn(mockUserLoginDto);

        //when
        UnregisteredUserException exception = assertThrows(UnregisteredUserException.class, () -> {
            kakaoLoginService.login(code);
        });

        //then
        assertEquals("가입되지 않은 사용자입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("[카카오 로그인]회원가입 된 사용자 카카오 로그인 시 유저 정보 및 토큰 발행")
    void givenRegisteredUser_whenKakoLoin_ThenReturnUserInfo() {
        //given
        String code = "mockCode";
        User mockUser = User.from(mockUserLoginDto);

        userRepository.save(mockUser);

        UserLoginDto expectedResponseDto = new UserLoginDto();
        expectedResponseDto.setEmail("test@example.com");
        expectedResponseDto.setAccessToken("mockAccessToken");
        expectedResponseDto.setRefreshToken("mockRefreshToken");


        when(kakaoApiService.getAccessToken(anyString())).thenReturn("mockAccessToken");
        when(kakaoApiService.getUserInfo(anyString())).thenReturn(mockUserLoginDto);
        when(responseMapper.toUserLoginDto(mockUser)).thenReturn(expectedResponseDto);
        when(jwtTokenProvider.createToken(mockUserLoginDto.getEmail(), mockUser.getId())).thenReturn("mockAccessToken");
        when(jwtTokenProvider.createRefreshToken(mockUserLoginDto.getEmail())).thenReturn("mockRefreshToken");

        //when
        UserLoginDto result = kakaoLoginService.login(code);

        // then
        assertNotNull(result);
        assertEquals(expectedResponseDto.getEmail(), result.getEmail());
        assertEquals(expectedResponseDto.getAccessToken(), result.getAccessToken());
        assertEquals(expectedResponseDto.getRefreshToken(), result.getRefreshToken());
    }
}
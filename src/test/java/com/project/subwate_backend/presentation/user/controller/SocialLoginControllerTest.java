package com.project.subwate_backend.presentation.user.controller;

import com.project.subwate_backend.user.application.service.GoogleLoginService;
import com.project.subwate_backend.user.application.service.KakaoLoginService;
import com.project.subwate_backend.user.application.service.SocialLoginService;
import com.project.subwate_backend.user.application.service.SocialLoginServiceFactory;
import com.project.subwate_backend.user.presentation.controller.SocialLoginController;
import com.project.subwate_backend.user.presentation.dto.response.UserLoginDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("소셜 oauth 로그인 컨트롤러 테스트")
@WebMvcTest(SocialLoginController.class)
class SocialLoginControllerTest {

    @MockBean
    private SocialLoginServiceFactory socialLoginServiceFactory;

    @MockBean
    private SocialLoginService socialLoginService;

    @MockBean
    private KakaoLoginService kakaoLoginService;

    @MockBean
    private GoogleLoginService googleLoginService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new SocialLoginController(socialLoginServiceFactory)).build();
    }

    @Test
    @DisplayName("카카오 oauth 로그인 컨트롤러 테스트")
    void givenTestKakaoUser_WhenKakaoLogin_ThenSuccess() throws Exception {
        //given
        UserLoginDto mockUserInfo = new UserLoginDto();
        mockUserInfo.setEmail("test@kakao.com");
        mockUserInfo.setNickname("testUser");


        //when
        when(socialLoginServiceFactory.getSocialLoginService("kakao")).thenReturn(kakaoLoginService);
        when(kakaoLoginService.login(anyString())).thenReturn(mockUserInfo);


        //then
        mockMvc.perform(get("/api/v1/auth/kakao/callback")
                        .param("code", "mockCode"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("로그인에 성공했습니다.")))
                .andExpect(jsonPath("$.data.email", is("test@kakao.com")))
                .andExpect(jsonPath("$.data.nickname", is("testUser")));

        verify(socialLoginServiceFactory).getSocialLoginService("kakao");
        verify(kakaoLoginService).login(anyString());
        verifyNoInteractions(googleLoginService);
    }

    @Test
    @DisplayName("구글 oauth 로그인 컨트롤러 테스트")
    void givenTestGoogleUser_WhenGoogleLogin_ThenSuccess() throws Exception {
        //given
        UserLoginDto mockUserInfo = new UserLoginDto();
        mockUserInfo.setEmail("test@google.com");
        mockUserInfo.setNickname("testUser");

        //when
        when(socialLoginServiceFactory.getSocialLoginService("google")).thenReturn(googleLoginService);
        when(googleLoginService.login(anyString())).thenReturn(mockUserInfo);

        //then
        mockMvc.perform(get("/api/v1/auth/google/callback")
                        .param("code", "mockCode"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("로그인에 성공했습니다.")))
                .andExpect(jsonPath("$.data.email", is("test@google.com")))
                .andExpect(jsonPath("$.data.nickname", is("testUser")));

        verify(socialLoginServiceFactory).getSocialLoginService("google");
        verify(googleLoginService).login(anyString());
        verifyNoInteractions(kakaoLoginService);
    }

}
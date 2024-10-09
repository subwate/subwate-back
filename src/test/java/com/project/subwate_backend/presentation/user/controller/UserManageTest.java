package com.project.subwate_backend.presentation.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.subwate_backend.application.user.dto.UserInfoDto;
import com.project.subwate_backend.application.user.service.UserManageService;
import com.project.subwate_backend.common.ResponseCode;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.presentation.user.dto.response.UserResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("소셜 oauth 회원가입 컨트롤러 테스트")
@WebMvcTest(controllers = UserManage.class)
@WithMockUser(username = "testUser", roles = "USER")
class UserManageTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserManageService userManageService;


    @Test
    @DisplayName("회원가입 성공 테스트")
    void givenUserLoginDto_WhenJoin_ThenSuccess() throws Exception {

        // given
        UserInfoDto mockUserinfoDto = new UserInfoDto();
        mockUserinfoDto.setEmail("test@kakao.com");
        mockUserinfoDto.setNickname("testUser");
        mockUserinfoDto.setName("test");
        mockUserinfoDto.setImage("http://test.com");
        mockUserinfoDto.setSocialLoginInfo("kakao");

        UserResponseDto mockUserResponseDto = new UserResponseDto();
        mockUserResponseDto.setName(mockUserinfoDto.getName());
        mockUserResponseDto.setEmail(mockUserinfoDto.getEmail());
        mockUserResponseDto.setNickname(mockUserinfoDto.getNickname());
        mockUserResponseDto.setImage(mockUserinfoDto.getImage());

        ResponseDto<UserResponseDto> mockResponse = ResponseDto.of(ResponseCode.USER_JOIN_SUCCESS, mockUserResponseDto);

        ObjectMapper objectMapper = new ObjectMapper();

        // when
        when(userManageService.join(any(UserInfoDto.class))).thenReturn(mockUserResponseDto);

        // then
        mockMvc.perform(post("/api/v1/user/join")
                        .with(csrf())  // CSRF 토큰 추가
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockUserinfoDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(ResponseCode.USER_JOIN_SUCCESS.getMessage())))
                .andExpect(jsonPath("$.data.email", is("test@kakao.com")))
                .andExpect(jsonPath("$.data.nickname", is("testUser")));

    }
}
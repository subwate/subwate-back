package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.application.exception.DuplicateUserException;
import com.project.subwate_backend.application.user.dto.UserInfoDto;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.user.entity.User;
import com.project.subwate_backend.infrastructure.user.repository.UserRepository;
import com.project.subwate_backend.presentation.user.dto.response.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserManageServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ResponseMapper responseMapper;

    @InjectMocks
    private UserManageServiceImpl userManageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 성공 테스트")
    void givenValidUserInfo_WhenJoin_ThenSuccess() {
        // given
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail("test@kakao.com");
        userInfoDto.setNickname("testUser");
        userInfoDto.setName("test");
        userInfoDto.setImage("http://test.com");

        User user = User.from(userInfoDto);
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(userInfoDto.getEmail());
        userResponseDto.setNickname(userInfoDto.getNickname());
        userResponseDto.setName(userInfoDto.getName());
        userResponseDto.setImage(userInfoDto.getImage());

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(responseMapper.toUserResponseDto(any(User.class))).thenReturn(userResponseDto);

        // when
        UserResponseDto result = userManageService.join(userInfoDto);

        // then
        verify(userRepository, times(1)).save(any(User.class));
        verify(responseMapper, times(1)).toUserResponseDto(any(User.class));
    }

    @Test
    @DisplayName("이메일 중복으로 인한 회원가입 실패 테스트")
    void givenDuplicateEmail_WhenJoin_ThenThrowDuplicateUserException() {
        // given
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail("duplicate@kakao.com");
        userInfoDto.setNickname("testUser");

        when(userRepository.existsByEmail(userInfoDto.getEmail())).thenReturn(true);

        // when
        Exception exception = assertThrows(DuplicateUserException.class, () -> userManageService.join(userInfoDto));

        // then
        assertTrue(exception instanceof DuplicateUserException);
        verify(userRepository, times(1)).existsByEmail(userInfoDto.getEmail());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    @DisplayName("닉네임 중복으로 인한 회원가입 실패 테스트")
    void givenDuplicateNickname_WhenJoin_ThenThrowDuplicateUserException() {
        // given
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setEmail("test@kakao.com");
        userInfoDto.setNickname("duplicateUser");

        when(userRepository.existsByNickname(userInfoDto.getNickname())).thenReturn(true);

        // when
        Exception exception = assertThrows(DuplicateUserException.class, () -> userManageService.join(userInfoDto));

        // then
        assertTrue(exception instanceof DuplicateUserException);
        verify(userRepository, times(1)).existsByNickname(userInfoDto.getNickname());
        verify(userRepository, never()).save(any(User.class));
    }
}
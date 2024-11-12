package com.project.subwate_backend.user.application.service;

import com.project.subwate_backend.user.application.exception.DuplicateUserException;
import com.project.subwate_backend.user.application.dto.UserInfoDto;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.user.domain.entity.User;
import com.project.subwate_backend.user.infrastructure.repository.UserRepository;
import com.project.subwate_backend.user.presentation.dto.response.UserResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.project.subwate_backend.user.application.UserResponseCode.USER_DUPLICATION_EMAIL;
import static com.project.subwate_backend.user.application.UserResponseCode.USER_DUPLICATION_NICKNAME;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserManageServiceImpl implements UserManageService {

    UserRepository userRepository;

    ResponseMapper responseMapper;

    @Override
    public UserResponseDto join(UserInfoDto userInfoDto) {

        isValidToJoin(userInfoDto);

        User user = userRepository.save(User.from(userInfoDto));

        return responseMapper.toUserResponseDto(user);
    }

    private void isValidToJoin(UserInfoDto userInfoDto) {
        if (userRepository.existsByEmail(userInfoDto.getEmail())) {
            throw new DuplicateUserException(USER_DUPLICATION_EMAIL);
        }

        if (userRepository.existsByNickname(userInfoDto.getNickname())) {
            throw new DuplicateUserException(USER_DUPLICATION_NICKNAME);
        }
    }

}

package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.application.exception.DuplicateUserException;
import com.project.subwate_backend.application.user.dto.UserInfoDto;
import com.project.subwate_backend.common.mapper.ResponseMapper;
import com.project.subwate_backend.domain.user.entity.User;
import com.project.subwate_backend.infrastructure.user.repository.UserRepository;
import com.project.subwate_backend.presentation.user.dto.response.UserResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.project.subwate_backend.common.ResponseCode.USER_DUPLICATION_EMAIL;
import static com.project.subwate_backend.common.ResponseCode.USER_DUPLICATION_NICKNAME;

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

package com.project.subwate_backend.application.user.service;

import com.project.subwate_backend.application.exception.DuplicateUserException;
import com.project.subwate_backend.domain.user.entity.User;
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
public class UserManageService implements UserManageServiceImpl {

    UserRepository userRepository;

    @Override
    public UserInfoDto join(UserInfoDto userInfoDto) {

        isValidToJoin(userInfoDto);

        userRepository.save(User.from(userInfoDto));

        userInfoDto.setJoinStatus(true);

        return userInfoDto;
    }

    private void isValidToJoin(UserInfoDto userInfoDto) {
        if (userRepository.existsByEmail(userInfoDto.getEmail())) {
            log.info("User already exists with email: {}", userInfoDto.getEmail());
            throw new DuplicateUserException("이미 가입 된 email 입니다");
        }

        if(userRepository.existsByNickname(userInfoDto.getNickname())) {
            log.info("User already exists with nickname: {}", userInfoDto.getNickname());
            throw new DuplicateUserException("이미 존재하는 nickname 입니다");
        }
    }

}

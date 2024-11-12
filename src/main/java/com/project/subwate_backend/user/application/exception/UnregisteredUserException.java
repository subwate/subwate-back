package com.project.subwate_backend.user.application.exception;

import com.project.subwate_backend.user.application.UserResponseCode;
import com.project.subwate_backend.user.presentation.dto.response.UserLoginDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import org.springframework.security.core.AuthenticationException;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UnregisteredUserException extends AuthenticationException {

    UserResponseCode userResponseCode;
    transient UserLoginDto userLoginDto;

    public UnregisteredUserException(UserResponseCode userResponseCode, UserLoginDto userLoginDto) {
        super(userResponseCode.getMessage());
        this.userResponseCode = userResponseCode;
        this.userLoginDto = userLoginDto;
    }
}

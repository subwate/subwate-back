package com.project.subwate_backend.application.exception;

import com.project.subwate_backend.common.ResponseCode;
import com.project.subwate_backend.presentation.user.dto.response.UserLoginDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import org.springframework.security.core.AuthenticationException;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UnregisteredUserException extends AuthenticationException {

    ResponseCode responseCode;
    transient UserLoginDto userLoginDto;

    public UnregisteredUserException(ResponseCode responseCode, UserLoginDto userLoginDto) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.userLoginDto = userLoginDto;
    }
}

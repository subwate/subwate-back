package com.project.subwate_backend.user.application.exception;

import com.project.subwate_backend.user.application.UserResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DuplicateUserException extends RuntimeException {

    UserResponseCode userResponseCode;

    public DuplicateUserException(UserResponseCode userResponseCode) {
        super(userResponseCode.getMessage());
        this.userResponseCode = userResponseCode;
    }
}

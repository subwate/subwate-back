package com.project.subwate_backend.user.infrastructure.exception;

import com.project.subwate_backend.user.application.UserResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OauthException extends RuntimeException {

    UserResponseCode userResponseCode;
    String detailMessage;

    public OauthException(UserResponseCode userResponseCode, String detailMessage) {
        super(userResponseCode.getMessage());
        this.userResponseCode = userResponseCode;
        this.detailMessage = detailMessage;
    }

    public OauthException(UserResponseCode userResponseCode, String detailMessage, Throwable cause) {
        super(userResponseCode.getMessage(), cause);
        this.userResponseCode = userResponseCode;
        this.detailMessage = detailMessage;
    }

}

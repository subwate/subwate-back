package com.project.subwate_backend.application.exception;

import com.project.subwate_backend.common.ResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DuplicateUserException extends RuntimeException {

    ResponseCode responseCode;

    public DuplicateUserException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }
}

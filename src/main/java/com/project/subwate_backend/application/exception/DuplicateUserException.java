package com.project.subwate_backend.application.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DuplicateUserException extends RuntimeException {

    HttpStatus status;

    public DuplicateUserException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }
}

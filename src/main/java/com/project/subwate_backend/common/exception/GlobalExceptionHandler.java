package com.project.subwate_backend.common.exception;

import com.project.subwate_backend.application.exception.DuplicateUserException;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.infrastructure.exception.OauthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Oauth 인증 및 값 처리 중 발생한 예외 처리
     *
     * @param ex Oauth 인증 실패 예외
     * @return ResponseDto
     */
    @ExceptionHandler(OauthException.class)
    public ResponseDto<String> handleOauthException(OauthException ex) {
        log.error(ex.getMessage());

        return ResponseDto.of(ex.getHttpStatus(), ex.getMessage(), null);
    }


    @ExceptionHandler(DuplicateUserException.class)
    public ResponseDto<String> handleDuplicateUserException(DuplicateUserException ex) {
        log.error(ex.getMessage());
        return ResponseDto.of(ex.getStatus(), ex.getMessage(), null);
    }
}

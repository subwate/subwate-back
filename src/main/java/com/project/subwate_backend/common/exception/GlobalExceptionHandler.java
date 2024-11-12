package com.project.subwate_backend.common.exception;

import com.project.subwate_backend.user.application.exception.DuplicateUserException;
import com.project.subwate_backend.user.application.exception.UnregisteredUserException;
import com.project.subwate_backend.user.application.UserResponseCode;
import com.project.subwate_backend.common.dto.ResponseDto;
import com.project.subwate_backend.user.infrastructure.exception.OauthException;
import com.project.subwate_backend.user.presentation.dto.response.UserLoginDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        log.error("[Details] " + ex.getDetailMessage());

        return ResponseDto.of(ex.getUserResponseCode(), null);
    }

    @ExceptionHandler(UnregisteredUserException.class)
    public ResponseDto<UserLoginDto> handleOauthException(UnregisteredUserException ex) {
        log.error(ex.getMessage());

        return ResponseDto.of(ex.getUserResponseCode(), ex.getUserLoginDto());
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseDto<String> handleDuplicateUserException(DuplicateUserException ex) {
        log.error(ex.getMessage());
        return ResponseDto.of(ex.getUserResponseCode(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDto<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        Map<String, String> errors = bindingResult.getFieldErrors().stream()
                .collect(Collectors
                        .toMap(FieldError::getField
                                , fieldError -> Optional
                                        .ofNullable(fieldError.getDefaultMessage())
                                        .orElse(" ")));

        return ResponseDto.of(UserResponseCode.VALID_CHECK_FAILED, errors);
    }
}

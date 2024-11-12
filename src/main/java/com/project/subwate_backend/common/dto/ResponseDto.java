package com.project.subwate_backend.common.dto;


import com.project.subwate_backend.user.application.UserResponseCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(staticName = "of") // TODO: 이전 swagger controller 호환용 개발 후 필요 없는 경우 삭제 필요
@Data
public class ResponseDto<D> {
    HttpStatus httpStatus;
    String message;
    D data;

    public static <D> ResponseDto<D> of(UserResponseCode userResponseCode, D data) {
        return new ResponseDto<>(userResponseCode.getHttpStatus(), userResponseCode.getMessage(), data);
    }
}


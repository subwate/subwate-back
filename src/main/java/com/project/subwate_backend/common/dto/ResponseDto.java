package com.project.subwate_backend.common.dto;

import com.project.subwate_backend.common.ResponseCode;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class ResponseDto<D> {
    HttpStatus httpStatus;
    String message;
    D data;

    public static <D> ResponseDto<D> of(ResponseCode responseCode, D data) {
        return new ResponseDto<>(responseCode.getHttpStatus(), responseCode.getMessage(), data);
    }
}
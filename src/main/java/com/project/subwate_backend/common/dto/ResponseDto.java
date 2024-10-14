package com.project.subwate_backend.common.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor(staticName = "of")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Data
public class ResponseDto<D> {
    HttpStatus httpStatus;
    String message;
    D data;
}

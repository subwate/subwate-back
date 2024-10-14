package com.project.subwate_backend.infrastructure.exception;

import com.project.subwate_backend.common.ResponseCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OauthException extends RuntimeException {

    ResponseCode responseCode;
    String detailMessage;

    public OauthException(ResponseCode responseCode, String detailMessage) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
        this.detailMessage = detailMessage;
    }

    public OauthException(ResponseCode responseCode, String detailMessage, Throwable cause) {
        super(responseCode.getMessage(), cause);
        this.responseCode = responseCode;
        this.detailMessage = detailMessage;
    }

}

package com.project.subwate_backend.infrastructure.exception.database;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.project.subwate_backend.common.dto.ResponseDto;

@ControllerAdvice
public class DatabaseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseExceptionHandler.class);

    @ExceptionHandler(DataAccessException.class)
    public ResponseDto<Void> handleDataAccessException(DataAccessException exception) {
        logger.error("Database error: ", exception);
        return ResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", null);
    }
}

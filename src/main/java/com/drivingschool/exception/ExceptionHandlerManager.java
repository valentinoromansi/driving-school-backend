package com.drivingschool.exception;

import ch.qos.logback.classic.Logger;
import com.drivingschool.domain.Question;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerManager {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleUnexpectedErrors(Exception e) {
        log.error(String.valueOf(e.getCause()));
    }

    @ExceptionHandler(DatabaseResourceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleUnexpectedErrors(DatabaseResourceException e) {
        log.error(String.valueOf(e.getMessage()));
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

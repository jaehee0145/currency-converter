package com.jaehee.currencyconverter.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity handleNullPointerException(NullPointerException e) {
        log.warn(e.getClass().getName() + " : " + e.getMessage());
        return ResponseEntity.status(400).contentType(MediaType.APPLICATION_JSON_UTF8)
                .body("{\"message\":" + e.getMessage() + "}");
    }
}

package com.ram.config2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AirplanNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AirplanNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String AirplanNotFoundHandler(AirplanNotFoundException ex) {
        return ex.getMessage();
    }
}
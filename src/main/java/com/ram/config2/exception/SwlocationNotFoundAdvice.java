package com.ram.config2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class SwlocationNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(SwlocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String SwlocationNotFoundHandler(SwlocationNotFoundException ex) {
        return ex.getMessage();
    }
}

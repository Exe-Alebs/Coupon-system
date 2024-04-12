package com.nxt.couponsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({CartNotFoundException.class, CouponNotFoundException.class,})
    public ResponseEntity<Object> handleCustomException(Exception ex) {
        String errorMessage = ex.getMessage();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // Default status code

        if (ex instanceof CartNotFoundException || ex instanceof CouponNotFoundException) {
            status = HttpStatus.NOT_FOUND;

        }

        return new ResponseEntity<>(errorMessage, status);
    }



}

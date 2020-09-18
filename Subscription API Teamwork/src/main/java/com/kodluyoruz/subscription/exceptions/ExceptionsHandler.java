package com.kodluyoruz.subscription.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.InvalidParameterException;
import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException exception){
        ExceptionDetails details = new ExceptionDetails(exception.getMessage(), new Date());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> internalServerErrorException(InternalServerErrorException exception){
        ExceptionDetails details = new ExceptionDetails(exception.getMessage(), new Date());

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> httpMessageNotReadableException(HttpMessageNotReadableException exception){
        ExceptionDetails details = new ExceptionDetails("One of the required parameter is null!", new Date());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<Object> invalidParameterException(InvalidParameterException exception){
        ExceptionDetails details = new ExceptionDetails(exception.getMessage(), new Date());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

}

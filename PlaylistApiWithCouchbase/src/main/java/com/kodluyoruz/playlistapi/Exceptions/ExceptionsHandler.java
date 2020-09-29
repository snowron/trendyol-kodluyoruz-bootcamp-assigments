package com.kodluyoruz.playlistapi.Exceptions;

import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.core.error.TimeoutException;
import com.fasterxml.jackson.databind.exc.ValueInstantiationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({DocumentNotFoundException.class})
    public ResponseEntity<Object> documentNotFoundException(DocumentNotFoundException exception) {
        ExceptionDetails details = new ExceptionDetails("There is no playlist as identified with this id.",
                new Date());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TimeoutException.class})
    public ResponseEntity<Object> timeoutException(TimeoutException exception) {
        ExceptionDetails details = new ExceptionDetails("Database timeout error please try again",
                new Date());

        return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ValueInstantiationException.class})
    public ResponseEntity<Object> allExceptions(ValueInstantiationException exception) {
        ExceptionDetails details = new ExceptionDetails(exception.getCause().toString(),
                new Date());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}

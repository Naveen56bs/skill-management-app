package com.nhs.skillmanagementservice.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.ValidationException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Exception Handler class to deal with all the exceptions.
 */
@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler({InvalidRequestException.class, ValidationException.class})
    public ResponseEntity<Object> handleBadRequestException(InvalidRequestException e) {
        ExceptionErrorResponse exceptionErrorResponse = new ExceptionErrorResponse(LocalDateTime.now(), e.getErrorMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionErrorResponse);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        ExceptionErrorResponse exceptionErrorResponse = new ExceptionErrorResponse(LocalDateTime.now(), e.getErrorMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exceptionErrorResponse);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleOtherException(Exception e) {
        ExceptionErrorResponse exceptionErrorResponse = new ExceptionErrorResponse(LocalDateTime.now(), e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exceptionErrorResponse);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ExceptionErrorResponse exceptionErrorResponse = new ExceptionErrorResponse(LocalDateTime.now(),
                ex.getBindingResult().getFieldErrors().stream().map(err -> err.getDefaultMessage()
                        + " at field: " + err.getField()).collect(Collectors.joining(", ")));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exceptionErrorResponse);
    }

    public ResponseEntity<Object> handleException(Exception ex) {
        if (ex instanceof InvalidRequestException) {
            InvalidRequestException e = (InvalidRequestException) ex;
            return handleBadRequestException(e);
        } else if (ex instanceof ResourceNotFoundException) {
            ResourceNotFoundException e = (ResourceNotFoundException) ex;
            return handleResourceNotFoundException(e);
        } else if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e = (MethodArgumentNotValidException) ex;
            return handleMethodArgumentNotValidException(e);
        } else {
            return handleOtherException(ex);
        }
    }
}
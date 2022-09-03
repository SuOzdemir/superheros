package com.dataguard.superhero.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {


    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse handleNullPointer(NullPointerException ex) {
        log.error("NullPointerException was uncaught by application: ", ex);
        return new RestErrorResponse(ex.getMessage());
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        RestErrorResponse restErrorResponse = new RestErrorResponse();

        for (ConstraintViolation violation : e.getConstraintViolations()) {
            restErrorResponse.getErrors().add(violation.getMessage());
        }
        return restErrorResponse;
    }
    @ExceptionHandler(value = {SuperheroNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestErrorResponse handleException(SuperheroNotFoundException ex) {
        log.error("SuperheroNotFoundException was uncaught by application: ", ex);
        return new RestErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestErrorResponse handleException(Exception ex) {
        log.error("Exception was uncaught by application: ", ex);
        return new RestErrorResponse(ex.getMessage());
    }
}

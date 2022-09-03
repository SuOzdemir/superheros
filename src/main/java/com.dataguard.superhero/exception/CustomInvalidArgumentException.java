package com.dataguard.superhero.exception;

public class CustomInvalidArgumentException extends RuntimeException {
    public CustomInvalidArgumentException(String message) {
        super(message);
    }

}

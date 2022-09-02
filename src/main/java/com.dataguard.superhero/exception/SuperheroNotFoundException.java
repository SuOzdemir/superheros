package com.dataguard.superhero.exception;

public class SuperheroNotFoundException extends CustomException {

    public SuperheroNotFoundException(String message) {
        super(message);
    }

    public SuperheroNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

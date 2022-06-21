package com.revature.ePort.util.custom_exception;

public class AuthenticationException extends RuntimeException{

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        super(message);
    }
}

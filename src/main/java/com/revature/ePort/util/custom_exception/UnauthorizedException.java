package com.revature.ePort.util.custom_exception;

public class UnauthorizedException extends RuntimeException{
    public UnauthorizedException() {
        super();
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}

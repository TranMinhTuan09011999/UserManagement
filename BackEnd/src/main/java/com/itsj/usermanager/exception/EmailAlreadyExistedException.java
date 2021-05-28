package com.itsj.usermanager.exception;

public class EmailAlreadyExistedException extends RuntimeException{
    public EmailAlreadyExistedException(String message) {
        super(message);
    }
}

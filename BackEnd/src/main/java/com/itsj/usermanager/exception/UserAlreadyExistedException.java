package com.itsj.usermanager.exception;

public class UserAlreadyExistedException extends RuntimeException{
    public UserAlreadyExistedException(String message) {
        super(message);
    }
}

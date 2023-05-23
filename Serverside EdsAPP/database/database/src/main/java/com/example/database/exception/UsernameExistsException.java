package com.example.database.exception;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException(String message){
        super(message);
    }

}
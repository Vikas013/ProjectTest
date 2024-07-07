package org.example.exceptions;

public class UserNotSubscribedException extends RuntimeException{
    public UserNotSubscribedException(String message){
        super(message);
    }
}

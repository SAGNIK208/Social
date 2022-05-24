package com.backend.social.exceptions;

public class UnauthorizedUserException extends  RuntimeException{
    public  UnauthorizedUserException(String message){
        super(message);
    }
}

package com.backend.social.exceptions;

public class InvalidPostException extends  RuntimeException{
    public InvalidPostException(String message){
        super(message);
    }
}

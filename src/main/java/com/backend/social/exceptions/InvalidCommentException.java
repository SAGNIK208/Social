package com.backend.social.exceptions;

public class InvalidCommentException extends  RuntimeException{
    public InvalidCommentException(String message){
        super(message);
    }
}
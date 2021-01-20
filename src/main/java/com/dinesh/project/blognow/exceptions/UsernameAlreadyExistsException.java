package com.dinesh.project.blognow.exceptions;

public class UsernameAlreadyExistsException  extends  RuntimeException{
    public UsernameAlreadyExistsException(String message){
        super(message);
    }
}

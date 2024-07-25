package com.medicalApi.controlExceptions;

public class EmailAlreadyExistsException  extends RuntimeException{
    public EmailAlreadyExistsException(String message){
        super(message);
    }
}

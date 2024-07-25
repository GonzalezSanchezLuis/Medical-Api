package com.medicalApi.controlExceptions;

public class DisabledUserException extends RuntimeException{
    public  DisabledUserException(String message){
        super(message);
    }
}

package com.lexai.lexaibackend.exception;

public class ResourceNotFoundException  extends RuntimeException{
    public ResourceNotFoundException(String message){
        super (message);
    }
}

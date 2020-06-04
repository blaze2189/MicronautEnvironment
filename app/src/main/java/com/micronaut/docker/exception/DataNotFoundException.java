package com.micronaut.docker.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException(Throwable throwable){
        super(throwable);
    }

    public DataNotFoundException(String message){
        super(message);
    }

    public DataNotFoundException(String message,Throwable throwable){
        super(message,throwable);
    }

}

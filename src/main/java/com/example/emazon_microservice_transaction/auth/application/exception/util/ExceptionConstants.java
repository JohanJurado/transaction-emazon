package com.example.emazon_microservice_transaction.auth.application.exception.util;

public enum ExceptionConstants {

    UNAUTHORIZED("You are not logged in. Please log in."),
    FORBIDDEN("You do not have permission to access this resource.");

    private final String message;

    ExceptionConstants(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }

}

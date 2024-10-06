package com.example.emazon_microservice_transaction.transaction.domain.exception;

public class IncorrectStockException extends RuntimeException {


    public IncorrectStockException(String message){
        super(message);
    }


}

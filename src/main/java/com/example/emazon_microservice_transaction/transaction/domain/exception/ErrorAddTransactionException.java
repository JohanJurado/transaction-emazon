package com.example.emazon_microservice_transaction.transaction.domain.exception;

public class ErrorAddTransactionException extends RuntimeException {
    public ErrorAddTransactionException(String message) {
        super(message);
    }
}

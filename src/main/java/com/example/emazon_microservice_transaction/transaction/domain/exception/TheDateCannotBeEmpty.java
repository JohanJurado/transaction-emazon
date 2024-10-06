package com.example.emazon_microservice_transaction.transaction.domain.exception;

public class TheDateCannotBeEmpty extends RuntimeException {
    public TheDateCannotBeEmpty(String message) {
        super(message);
    }
}

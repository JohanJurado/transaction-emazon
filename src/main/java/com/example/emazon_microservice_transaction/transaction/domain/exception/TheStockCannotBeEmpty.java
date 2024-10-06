package com.example.emazon_microservice_transaction.transaction.domain.exception;

public class TheStockCannotBeEmpty extends RuntimeException {
    public TheStockCannotBeEmpty(String message) {
        super(message);
    }
}

package com.example.emazon_microservice_transaction.transaction.domain.exception;

public class TheNameArticleCannotBeEmpty extends RuntimeException {
    public TheNameArticleCannotBeEmpty(String message) {
        super(message);
    }
}

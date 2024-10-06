package com.example.emazon_microservice_transaction.transaction.application.exceptionhandler;

import com.example.emazon_microservice_transaction.transaction.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisorTransaction {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(IncorrectStockException.class)
    public ResponseEntity<Map<String, String>> handleIncorrectStockException(
            IncorrectStockException incorrectStockException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, incorrectStockException.getMessage()));
    }

    @ExceptionHandler(ErrorAddTransactionException.class)
    public ResponseEntity<Map<String, String>> handleErrorAddTransactionException(
            ErrorAddTransactionException errorAddTransactionException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, errorAddTransactionException.getMessage()));
    }

    @ExceptionHandler(TheDateCannotBeEmpty.class)
    public ResponseEntity<Map<String, String>> handleTheDateCannotBeEmpty(
            TheDateCannotBeEmpty theDateCannotBeEmpty) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, theDateCannotBeEmpty.getMessage()));
    }

    @ExceptionHandler(TheNameArticleCannotBeEmpty.class)
    public ResponseEntity<Map<String, String>> handleTheNameArticleCannotBeEmpty(
            TheNameArticleCannotBeEmpty theNameArticleCannotBeEmpty) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, theNameArticleCannotBeEmpty.getMessage()));
    }

    @ExceptionHandler(TheStockCannotBeEmpty.class)
    public ResponseEntity<Map<String, String>> handleTheStockCannotBeEmpty(
            TheStockCannotBeEmpty theStockCannotBeEmpty) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, theStockCannotBeEmpty.getMessage()));
    }
}

package com.example.emazon_microservice_transaction.transaction.infraestructure.input.rest;

import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionRequest;
import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionResponse;
import com.example.emazon_microservice_transaction.transaction.application.handler.ITransactionHandler;
import com.example.emazon_microservice_transaction.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTest {

    @Mock
    private ITransactionHandler transactionHandler;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionRequest transactionRequest;
    private TransactionResponse transactionResponse;
    private final Constants constants = new Constants();

    @BeforeEach
    void setUp() {

        transactionRequest = new TransactionRequest();
        transactionResponse = new TransactionResponse();
    }

    @Test
    void shouldReturnCreatedWhenTransactionAdded() {
        when(transactionHandler.addTransaction(any(TransactionRequest.class))).thenReturn(transactionResponse);

        ResponseEntity<TransactionResponse> response = transactionController.addTransaction(transactionRequest);

        verify(transactionHandler, times(constants.ONE_TIME)).addTransaction(transactionRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(transactionResponse, response.getBody());
    }
}

package com.example.emazon_microservice_transaction.transaction.application.handler;

import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionRequest;
import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionResponse;
import com.example.emazon_microservice_transaction.transaction.application.mapper.ITransactionMapper;
import com.example.emazon_microservice_transaction.transaction.domain.api.ITransactionServicePort;
import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;
import com.example.emazon_microservice_transaction.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionHandlerTest {

    @Mock
    private ITransactionServicePort transactionServicePort;

    @Mock
    private ITransactionMapper transactionMapper;

    @InjectMocks
    private TransactionHandler transactionHandler;

    private TransactionRequest transactionRequest;
    private Transaction transaction;
    private TransactionResponse transactionResponse;
    private final Constants constants = new Constants();

    @BeforeEach
    void setUp() {

        transactionRequest = new TransactionRequest();
        transaction = new Transaction();
        transactionResponse = new TransactionResponse();
    }

    @Test
    void shouldAddTransactionSuccessfully() {
        when(transactionMapper.toTransaction(transactionRequest)).thenReturn(transaction);
        when(transactionServicePort.addTransaction(transaction)).thenReturn(transaction);
        when(transactionMapper.toTransactionResponse(transaction)).thenReturn(transactionResponse);

        TransactionResponse result = transactionHandler.addTransaction(transactionRequest);

        verify(transactionMapper, times(constants.ONE_TIME)).toTransaction(transactionRequest);
        verify(transactionServicePort, times(constants.ONE_TIME)).addTransaction(transaction);
        verify(transactionMapper, times(constants.ONE_TIME)).toTransactionResponse(transaction);
        assertEquals(transactionResponse, result);
    }
}

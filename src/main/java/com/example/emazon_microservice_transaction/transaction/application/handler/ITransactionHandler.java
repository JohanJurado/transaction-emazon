package com.example.emazon_microservice_transaction.transaction.application.handler;

import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionRequest;
import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionResponse;

public interface ITransactionHandler {

    TransactionResponse addTransaction(TransactionRequest transactionRequest);

}

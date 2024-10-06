package com.example.emazon_microservice_transaction.transaction.domain.api;

import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;

public interface ITransactionServicePort {

    Transaction addTransaction(Transaction transaction);

}

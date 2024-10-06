package com.example.emazon_microservice_transaction.transaction.domain.spi;

import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;

public interface ITransactionPersistencePort {

    Transaction save(Transaction transaction);

    Transaction findLastTransactionByNameArticle(String nameArticle);

    void delete(Transaction transaction);

}

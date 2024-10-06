package com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.adapter;

import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;
import com.example.emazon_microservice_transaction.transaction.domain.spi.ITransactionPersistencePort;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.mapper.ITransactionEntityMapper;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.repository.ITransactionJpaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionJpaAdapter implements ITransactionPersistencePort {

    private final ITransactionEntityMapper transactionEntityMapper;
    private final ITransactionJpaRepository transactionJpaRepository;

    @Override
    public Transaction save(Transaction transaction) {
        return transactionEntityMapper.toTransaction(
                transactionJpaRepository.save(
                        transactionEntityMapper.toTransactionEntity(transaction)
                )
        );
    }

    @Override
    public Transaction findLastTransactionByNameArticle(String nameArticle) {
        return transactionEntityMapper.toTransaction(
            transactionJpaRepository.findLastTransactionByNameArticle(nameArticle).orElse(null)
        );
    }

    @Override
    public void delete(Transaction transaction) {
        transactionJpaRepository.delete(transactionEntityMapper.toTransactionEntity(transaction));
    }
}

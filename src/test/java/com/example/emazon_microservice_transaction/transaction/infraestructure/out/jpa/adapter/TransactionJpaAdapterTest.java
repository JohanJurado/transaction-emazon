package com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.adapter;

import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.entity.TransactionEntity;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.mapper.ITransactionEntityMapper;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.repository.ITransactionJpaRepository;
import com.example.emazon_microservice_transaction.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionJpaAdapterTest {

    @Mock
    private ITransactionEntityMapper transactionEntityMapper;

    @Mock
    private ITransactionJpaRepository transactionJpaRepository;

    @InjectMocks
    private TransactionJpaAdapter transactionJpaAdapter;

    private Transaction transaction;
    private TransactionEntity transactionEntity;
    private final Constants constants = new Constants();

    @BeforeEach
    void setUp() {
        transaction = new Transaction();
        transactionEntity = new TransactionEntity();
    }

    @Test
    void shouldSaveTransactionSuccessfully() {
        when(transactionEntityMapper.toTransactionEntity(transaction)).thenReturn(transactionEntity);
        when(transactionJpaRepository.save(any())).thenReturn(transactionEntity);
        when(transactionEntityMapper.toTransaction(any())).thenReturn(transaction);

        Transaction result = transactionJpaAdapter.save(transaction);

        verify(transactionJpaRepository, times(1)).save(any());
        assertEquals(transaction, result);
    }

    @Test
    void shouldFindLastTransactionByNameArticle() {
        when(transactionJpaRepository.findLastTransactionByNameArticle(constants.NAME_ARTICLE)).thenReturn(Optional.of(transactionEntity));
        when(transactionEntityMapper.toTransaction(any())).thenReturn(transaction);

        Transaction result = transactionJpaAdapter.findLastTransactionByNameArticle(constants.NAME_ARTICLE);

        verify(transactionJpaRepository, times(1)).findLastTransactionByNameArticle(anyString());
        assertNotNull(result);
    }

    @Test
    void shouldDeleteTransactionSuccessfully() {
        when(transactionEntityMapper.toTransactionEntity(transaction)).thenReturn(transactionEntity);

        transactionJpaAdapter.delete(transaction);

        verify(transactionJpaRepository, times(1)).delete(any());
    }
}

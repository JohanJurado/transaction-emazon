package com.example.emazon_microservice_transaction.transaction.domain.usecase;

import com.example.emazon_microservice_transaction.transaction.domain.api.IStockFeignServicePort;
import com.example.emazon_microservice_transaction.transaction.domain.exception.*;
import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;
import com.example.emazon_microservice_transaction.transaction.domain.spi.ITransactionPersistencePort;
import com.example.emazon_microservice_transaction.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionUseCaseTest {

    @Mock
    private ITransactionPersistencePort transactionPersistencePort;

    @Mock
    private IStockFeignServicePort stockFeignServicePort;

    @InjectMocks
    private TransactionUseCase transactionUseCase;

    private Transaction validTransaction;
    private final Constants constants = new Constants();

    @BeforeEach
    void setUp() {
        validTransaction = new Transaction();
        validTransaction.setNameArticle(constants.NAME_ARTICLE);
        validTransaction.setStockTransaction(constants.STOCK_TRANSACTION);
        validTransaction.setDateTransaction(constants.DATE_TRANSACTION);
    }

    @Test
    void shouldThrowIncorrectStockExceptionWhenStockIsBelowMinimum() {
        validTransaction.setStockTransaction(constants.INCORRECT_STOCK_TRANSACTION);

        assertThrows(IncorrectStockException.class, () -> transactionUseCase.addTransaction(validTransaction));
    }

    @Test
    void shouldThrowTheNameArticleCannotBeEmptyWhenNameIsEmpty() {
        validTransaction.setNameArticle(constants.EMPTY_NAME);

        assertThrows(TheNameArticleCannotBeEmpty.class, () -> transactionUseCase.addTransaction(validTransaction));
    }

    @Test
    void shouldThrowTheStockCannotBeEmptyWhenStockIsNull() {
        Transaction stockNullTransaction = constants.STOCK_NULL_TRANSACTION;

        assertThrows(TheStockCannotBeEmpty.class, () -> transactionUseCase.addTransaction(stockNullTransaction));
    }

    @Test
    void shouldThrowTheDateCannotBeEmpty() {
        Transaction dateNullTransaction = constants.DATE_NULL_TRANSACTION;

        assertThrows(TheDateCannotBeEmpty.class, () -> transactionUseCase.addTransaction(dateNullTransaction));
    }

    @Test
    void shouldAddTransactionSuccessfully() {
        when(transactionPersistencePort.save(any(Transaction.class))).thenReturn(validTransaction);
        Transaction result = transactionUseCase.addTransaction(validTransaction);

        verify(stockFeignServicePort, times(constants.ONE_TIME)).updateStock(validTransaction.getNameArticle(), validTransaction.getStockTransaction());
        verify(transactionPersistencePort, times(constants.ONE_TIME)).save(validTransaction);
        assertNotNull(result);
    }

    @Test
    void shouldHandleErrorAddTransactionException() {
        when(transactionPersistencePort.save(any(Transaction.class))).thenThrow(new RuntimeException());
        when(transactionPersistencePort.findLastTransactionByNameArticle(validTransaction.getNameArticle())).thenReturn(validTransaction);

        assertThrows(ErrorAddTransactionException.class, () -> transactionUseCase.addTransaction(validTransaction));

        verify(transactionPersistencePort, times(constants.ONE_TIME)).delete(validTransaction);
    }
}

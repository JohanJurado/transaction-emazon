package com.example.emazon_microservice_transaction.transaction.domain.usecase;

import com.example.emazon_microservice_transaction.transaction.domain.api.IStockFeignServicePort;
import com.example.emazon_microservice_transaction.transaction.domain.api.ITransactionServicePort;
import com.example.emazon_microservice_transaction.transaction.domain.exception.*;
import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;
import com.example.emazon_microservice_transaction.transaction.domain.spi.ITransactionPersistencePort;
import com.example.emazon_microservice_transaction.transaction.domain.util.ConstantsTransaction;
import com.example.emazon_microservice_transaction.transaction.domain.util.ExceptionConstants;

public class TransactionUseCase implements ITransactionServicePort {

    private final ITransactionPersistencePort transactionPersistencePort;
    private final IStockFeignServicePort stockFeignServicePort;

    public TransactionUseCase(ITransactionPersistencePort transactionPersistencePort, IStockFeignServicePort stockFeignServicePort) {
        this.transactionPersistencePort = transactionPersistencePort;
        this.stockFeignServicePort = stockFeignServicePort;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {

        transaction.setNameArticle(transaction.getNameArticle().trim());

        if (transaction.getStockTransaction() == null) {
            throw new TheStockCannotBeEmpty(ExceptionConstants.THE_STOCK_CANNOT_BE_EMPTY.getMessage());
        } else if (transaction.getStockTransaction() <= ConstantsTransaction.STOCK_MIN) {
            throw new IncorrectStockException(ExceptionConstants.INCORRECT_STOCK.getMessage());
        }

        if (transaction.getNameArticle().isEmpty()) {
            throw new TheNameArticleCannotBeEmpty(ExceptionConstants.THE_NAME_ARTICLE_CANNOT_BE_EMPTY.getMessage());
        }

        if (transaction.getDateTransaction() == null) {
            throw new TheDateCannotBeEmpty(ExceptionConstants.THE_DATE_CANNOT_BE_EMPTY.getMessage());
        }

        stockFeignServicePort.updateStock(transaction.getNameArticle(), transaction.getStockTransaction());

        try {
            return transactionPersistencePort.save(transaction);
        } catch (Exception e) {
            Transaction transactionDelete = transactionPersistencePort.findLastTransactionByNameArticle(transaction.getNameArticle());
            transactionPersistencePort.delete(transactionDelete);
            throw new ErrorAddTransactionException(ExceptionConstants.ERROR_ADD_TRANSACTION.getMessage());
        }
    }

}

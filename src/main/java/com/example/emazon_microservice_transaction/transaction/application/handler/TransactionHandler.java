package com.example.emazon_microservice_transaction.transaction.application.handler;

import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionRequest;
import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionResponse;
import com.example.emazon_microservice_transaction.transaction.application.mapper.ITransactionMapper;
import com.example.emazon_microservice_transaction.transaction.domain.api.ITransactionServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionHandler implements ITransactionHandler{

    private final ITransactionServicePort transactionServicePort;
    private final ITransactionMapper transactionMapper;

    @Override
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {
        return transactionMapper.toTransactionResponse(
                transactionServicePort.addTransaction(
                        transactionMapper.toTransaction(transactionRequest)
                )
        );
    }
}

package com.example.emazon_microservice_transaction.transaction.application.mapper;

import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionRequest;
import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionResponse;
import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITransactionMapper {

    @Mapping(target = "idTransaction", ignore = true)
    @Mapping(source = "nameArticle", target="nameArticle")
    @Mapping(source = "stockTransaction", target="stockTransaction")
    @Mapping(source = "dateTransaction", target="dateTransaction")
    Transaction toTransaction(TransactionRequest transactionRequest);

    @Mapping(source = "idTransaction", target="idTransaction")
    @Mapping(source = "nameArticle", target="nameArticle")
    @Mapping(source = "stockTransaction", target="stockTransaction")
    @Mapping(source = "dateTransaction", target="dateTransaction")
    TransactionResponse toTransactionResponse(Transaction transaction);
}

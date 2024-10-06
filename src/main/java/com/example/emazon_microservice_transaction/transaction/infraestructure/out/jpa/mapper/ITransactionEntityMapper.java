package com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.mapper;


import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionRequest;
import com.example.emazon_microservice_transaction.transaction.application.dto.TransactionResponse;
import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ITransactionEntityMapper{

    @Mapping(source = "idTransaction", target="idTransaction")
    @Mapping(source = "nameArticle", target="nameArticle")
    @Mapping(source = "stockTransaction", target="stockTransaction")
    @Mapping(source = "dateTransaction", target="dateTransaction")
    TransactionEntity toTransactionEntity(Transaction transaction);

    @Mapping(source = "idTransaction", target="idTransaction")
    @Mapping(source = "nameArticle", target="nameArticle")
    @Mapping(source = "stockTransaction", target="stockTransaction")
    @Mapping(source = "dateTransaction", target="dateTransaction")
    Transaction toTransaction(TransactionEntity transactionEntity);

}

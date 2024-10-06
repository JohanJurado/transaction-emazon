package com.example.emazon_microservice_transaction.util;

import com.example.emazon_microservice_transaction.transaction.domain.model.Transaction;

import java.time.LocalDate;

public class Constants {

    public String NAME_ARTICLE = "article 1";
    public String EMPTY_NAME = " ";

    public Long STOCK_TRANSACTION = 10l;
    public Long INCORRECT_STOCK_TRANSACTION = 0l;
    public Long NULL_STOCK_TRANSACTION = null;

    public LocalDate DATE_TRANSACTION = LocalDate.parse("2024-01-01");
    public LocalDate NULL_DATE = null;

    public Integer ONE_TIME = 1;


    public Transaction STOCK_NULL_TRANSACTION = new Transaction(1L, NAME_ARTICLE, NULL_STOCK_TRANSACTION, DATE_TRANSACTION);
    public Transaction DATE_NULL_TRANSACTION = new Transaction(1L, NAME_ARTICLE, STOCK_TRANSACTION, NULL_DATE);
}

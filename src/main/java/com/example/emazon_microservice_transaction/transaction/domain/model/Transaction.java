package com.example.emazon_microservice_transaction.transaction.domain.model;

import java.time.LocalDate;

public class Transaction {

    private Long idTransaction;
    private String nameArticle;
    private Long stockTransaction;
    private LocalDate dateTransaction;

    public Transaction() {
    }

    public Transaction(Long idTransaction, String nameArticle, Long stockTransaction, LocalDate dateTransaction) {
        this.idTransaction = idTransaction;
        this.nameArticle = nameArticle;
        this.stockTransaction = stockTransaction;
        this.dateTransaction = dateTransaction;
    }

    public Long getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(Long idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getNameArticle() {
        return nameArticle;
    }

    public void setNameArticle(String nameArticle) {
        this.nameArticle = nameArticle;
    }

    public Long getStockTransaction() {
        return stockTransaction;
    }

    public void setStockTransaction(Long stockTransaction) {
        this.stockTransaction = stockTransaction;
    }

    public LocalDate getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(LocalDate dateTransaction) {
        this.dateTransaction = dateTransaction;
    }
}

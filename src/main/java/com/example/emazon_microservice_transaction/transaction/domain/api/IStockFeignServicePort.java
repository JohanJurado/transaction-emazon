package com.example.emazon_microservice_transaction.transaction.domain.api;

public interface IStockFeignServicePort {

    void updateStock(String nameArticle, Long stock);

}

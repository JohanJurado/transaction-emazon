package com.example.emazon_microservice_transaction.transaction.infraestructure.out.feign.adapter;

import com.example.emazon_microservice_transaction.transaction.domain.api.IStockFeignServicePort;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.feign.IStockFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockFeignAdapter implements IStockFeignServicePort {

    private final IStockFeignClient stockFeignClient;

    @Override
    public void updateStock(String nameArticle, Long stock) {
        stockFeignClient.updateStock(nameArticle, stock);
    }
}

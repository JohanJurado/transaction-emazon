package com.example.emazon_microservice_transaction.transaction.infraestructure.out.feign.adapter;

import com.example.emazon_microservice_transaction.transaction.infraestructure.out.feign.IStockFeignClient;
import com.example.emazon_microservice_transaction.util.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StockFeignAdapterTest {

    @Mock
    private IStockFeignClient stockFeignClient;

    @InjectMocks
    private StockFeignAdapter stockFeignAdapter;

    private final Constants constants = new Constants();

    @Test
    void shouldUpdateStockSuccessfully() {
        String nameArticle = constants.NAME_ARTICLE;
        Long stock = constants.STOCK_TRANSACTION;

        stockFeignAdapter.updateStock(nameArticle, stock);

        verify(stockFeignClient, times(constants.ONE_TIME)).updateStock(nameArticle, stock);
    }
}

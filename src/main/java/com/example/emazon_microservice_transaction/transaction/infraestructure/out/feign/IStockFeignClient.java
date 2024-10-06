package com.example.emazon_microservice_transaction.transaction.infraestructure.out.feign;

import com.example.emazon_microservice_transaction.transaction.infraestructure.configuration.FeignClientSecurityConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "stock-service", url = "http://localhost:8080/article", configuration = FeignClientSecurityConfiguration.class)
public interface IStockFeignClient {

    @PutMapping("/{nameArticle}/{stock}")
    void updateStock(
            @PathVariable("nameArticle") String nameArticle,
            @PathVariable("stock") Long stock
    );
}
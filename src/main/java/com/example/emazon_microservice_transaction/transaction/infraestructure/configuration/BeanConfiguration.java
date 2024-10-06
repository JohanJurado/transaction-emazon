package com.example.emazon_microservice_transaction.transaction.infraestructure.configuration;


import com.example.emazon_microservice_transaction.transaction.application.exceptionhandler.FeignClientErrorDecoder;
import com.example.emazon_microservice_transaction.transaction.domain.api.IStockFeignServicePort;
import com.example.emazon_microservice_transaction.transaction.domain.api.ITransactionServicePort;
import com.example.emazon_microservice_transaction.transaction.domain.spi.ITransactionPersistencePort;
import com.example.emazon_microservice_transaction.transaction.domain.usecase.TransactionUseCase;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.feign.IStockFeignClient;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.feign.adapter.StockFeignAdapter;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.adapter.TransactionJpaAdapter;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.mapper.ITransactionEntityMapper;
import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.repository.ITransactionJpaRepository;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ITransactionEntityMapper transactionEntityMapper;
    private final ITransactionJpaRepository transactionJpaRepository;

    @Bean
    public ITransactionPersistencePort transactionPersistencePort(){
        return new TransactionJpaAdapter(transactionEntityMapper, transactionJpaRepository);
    }

    @Bean
    public IStockFeignServicePort stockFeignServicePort(IStockFeignClient stockFeignClient){
        return new StockFeignAdapter(stockFeignClient);
    }

    @Bean
    public ITransactionServicePort transactionServicePort(
            ITransactionPersistencePort transactionPersistencePort,
            IStockFeignServicePort stockFeignServicePort
    ) {
        return new TransactionUseCase(transactionPersistencePort, stockFeignServicePort);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientErrorDecoder();
    }
}

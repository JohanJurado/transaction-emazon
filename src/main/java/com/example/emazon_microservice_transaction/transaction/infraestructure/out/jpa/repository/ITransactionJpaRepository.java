package com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.repository;

import com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ITransactionJpaRepository extends JpaRepository<TransactionEntity, Long> {

    @Query("SELECT t FROM TransactionEntity t WHERE t.nameArticle = :nameArticle ORDER BY t.idTransaction DESC")
    Optional<TransactionEntity> findLastTransactionByNameArticle(String nameArticle);

}

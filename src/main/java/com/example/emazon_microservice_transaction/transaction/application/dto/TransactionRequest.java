package com.example.emazon_microservice_transaction.transaction.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String nameArticle;
    private Long stockTransaction;
    private LocalDate dateTransaction;

}

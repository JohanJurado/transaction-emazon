package com.example.emazon_microservice_transaction.transaction.infraestructure.out.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long idTransaction;

    @Column(name = "name_article")
    private String nameArticle;

    @Column(name = "stock_transaction")
    private Long stockTransaction;

    @Column(name = "date_transaction")
    private LocalDate dateTransaction;

}

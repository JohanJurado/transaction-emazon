package com.example.emazon_microservice_transaction.transaction.domain.util;

public enum ExceptionConstants {

    THE_DATE_CANNOT_BE_EMPTY("The phone has exceeded the maximum number allowed: 13 < "),
    THE_NAME_ARTICLE_CANNOT_BE_EMPTY("The phone format entered is not allowed"),
    THE_STOCK_CANNOT_BE_EMPTY("Rol not found: "),
    ARTICLE_NOT_FOUND("Article not found: "),
    ERROR_UPDATE_ARTICLE("An error occurred while updating the article"),
    ERROR_ADD_TRANSACTION("An error occurred while create the transaction, the changes were reverted"),
    INCORRECT_STOCK("The stock does not meet the requirements");

    private final String message;

     ExceptionConstants(String message){
            this.message = message;
        }

     public String getMessage(){
         return this.message;
     }
}

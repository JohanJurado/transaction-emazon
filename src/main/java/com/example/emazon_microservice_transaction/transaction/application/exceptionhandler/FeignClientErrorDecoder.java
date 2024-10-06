package com.example.emazon_microservice_transaction.transaction.application.exceptionhandler;

import com.example.emazon_microservice_transaction.transaction.domain.util.ExceptionConstants;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignClientErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        switch (status) {
            case NOT_FOUND: // 404
                return new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionConstants.ARTICLE_NOT_FOUND.getMessage());
            case BAD_REQUEST: // 400
                return new ResponseStatusException(HttpStatus.CONFLICT, ExceptionConstants.ERROR_UPDATE_ARTICLE.getMessage());
//            case FORBIDDEN: // 403
//                return new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied to Stock service");
            default:
                return defaultErrorDecoder.decode(methodKey, response);
        }
    }
}

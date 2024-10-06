package com.example.emazon_microservice_transaction.transaction.infraestructure.configuration;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignClientSecurityConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            // Verifica si la autenticación es de tipo CustomJwtAuthenticationToken
            if (SecurityContextHolder.getContext().getAuthentication() instanceof CustomJwtAuthenticationToken) {
                CustomJwtAuthenticationToken jwtToken = (CustomJwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
                String tokenValue = jwtToken.getClaims().getSubject(); // O usa el valor correcto del token
                requestTemplate.header("Authorization", "Bearer " + tokenValue);
            }
        };
    }
}
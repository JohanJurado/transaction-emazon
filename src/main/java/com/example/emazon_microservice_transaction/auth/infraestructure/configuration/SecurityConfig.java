package com.example.emazon_microservice_transaction.auth.infraestructure.configuration;

import com.example.emazon_microservice_transaction.auth.application.exception.CustomAccessDeniedHandler;
import com.example.emazon_microservice_transaction.auth.application.exception.CustomAuthenticationEntryPoint;
import com.example.emazon_microservice_transaction.auth.application.service.JwtTokenValidator;
import com.example.emazon_microservice_transaction.auth.infraestructure.configuration.filter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_WAREHOUSE_ASSISTANT = "WAREHOUSE_ASSISTANT";
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement( sessionMangConfig -> sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS) )
                .addFilterBefore(new JwtTokenFilter(jwtTokenValidator()), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( auth -> {
                    // Verificar si el usuario tiene el rol "WAREHOUSE_ASSISTANT" para acceder a estos endpoints
                    auth.requestMatchers(HttpMethod.POST, "/transaction").hasAuthority(ROLE_WAREHOUSE_ASSISTANT);

                    auth.requestMatchers("/error").permitAll();
                    auth.anyRequest().permitAll();
                })
                .exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
                .build();
    }

    @Bean
    public JwtTokenValidator jwtTokenValidator() {
        return new JwtTokenValidator();
    }

}


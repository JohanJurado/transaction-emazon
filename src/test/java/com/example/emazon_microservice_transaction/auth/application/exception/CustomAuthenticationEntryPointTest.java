package com.example.emazon_microservice_transaction.auth.application.exception;

import com.example.emazon_microservice_transaction.auth.application.exception.CustomAuthenticationEntryPoint;
import com.example.emazon_microservice_transaction.auth.application.exception.util.ExceptionConstants;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomAuthenticationEntryPointTest {

    @InjectMocks
    private CustomAuthenticationEntryPoint authenticationEntryPoint;

    private HttpServletRequest request;
    private HttpServletResponse response;
    private ServletOutputStream outputStream;

    @BeforeEach
    void setUp() throws IOException{
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        outputStream = mock(ServletOutputStream.class);

        when(response.getOutputStream()).thenReturn(outputStream);
    }

    @Test
    void shouldReturnUnauthorizedStatusAndMessage() throws Exception {
        authenticationEntryPoint.commence(request, response, null);

        verify(response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        verify(response).setContentType("application/json");
        verify(outputStream).println(ExceptionConstants.UNAUTHORIZED.getMessage()); // Verificar que el mensaje sea escrito

    }
}

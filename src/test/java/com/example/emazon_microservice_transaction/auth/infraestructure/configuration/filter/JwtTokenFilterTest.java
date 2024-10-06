package com.example.emazon_microservice_transaction.auth.infraestructure.configuration.filter;

import com.example.emazon_microservice_transaction.auth.application.service.JwtTokenValidator;
import com.example.emazon_microservice_transaction.util.ConstantsAuth;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenFilterTest {

    @Mock
    private JwtTokenValidator jwtTokenValidator;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @InjectMocks
    private JwtTokenFilter jwtTokenFilter;

    private String BEARER_TOKEN;
    private String JWT_TOKEN;
    private String AUTHORIZATION;
    private String ROLE;
    private String CLAIM_ROLE_PARAM;

    @BeforeEach
    void setUp() {
        ConstantsAuth constantsAuth = new ConstantsAuth();

        BEARER_TOKEN = constantsAuth.BEARER_TOKEN;
        JWT_TOKEN = constantsAuth.JWT_TOKEN;
        AUTHORIZATION = constantsAuth.AUTHORIZATION;
        ROLE = constantsAuth.ROLE;
        CLAIM_ROLE_PARAM = constantsAuth.CLAIM_ROLE_PARAM;
    }

    @Test
    void shouldAuthenticateUserWhenTokenIsValid() throws Exception {
        Claims claims = mock(Claims.class);
        when(request.getHeader(AUTHORIZATION)).thenReturn(BEARER_TOKEN);
        when(jwtTokenValidator.validateToken(JWT_TOKEN)).thenReturn(claims);
        when(jwtTokenValidator.isTokenExpired(claims)).thenReturn(false);
        when(claims.get(CLAIM_ROLE_PARAM, String.class)).thenReturn(ROLE);

        SecurityContext securityContext = mock(SecurityContext.class);
        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            jwtTokenFilter.doFilterInternal(request, response, filterChain);

            verify(securityContext).setAuthentication(any(CustomJwtAuthenticationToken.class));
            verify(filterChain).doFilter(request, response);
        }
    }

    @Test
    void shouldNotAuthenticateWhenTokenIsExpired() throws Exception {
        // Simulamos SecurityContext y SecurityContextHolder
        SecurityContext securityContext = mock(SecurityContext.class);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            // Configurar el comportamiento de SecurityContextHolder.getContext()
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            Claims claims = mock(Claims.class);
            when(request.getHeader(AUTHORIZATION)).thenReturn(BEARER_TOKEN);
            when(jwtTokenValidator.validateToken(JWT_TOKEN)).thenReturn(claims);
            when(jwtTokenValidator.isTokenExpired(claims)).thenReturn(true);

            jwtTokenFilter.doFilterInternal(request, response, filterChain);

            verify(SecurityContextHolder.getContext(), never()).setAuthentication(any());
            verify(filterChain).doFilter(request, response);
            }
    }

    @Test
    void shouldDoNothingWhenNoTokenIsProvided() throws Exception {

        SecurityContext securityContext = mock(SecurityContext.class);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder = mockStatic(SecurityContextHolder.class)) {
            // Configurar el comportamiento de SecurityContextHolder.getContext()
            mockedSecurityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(request.getHeader(AUTHORIZATION)).thenReturn(null);

            jwtTokenFilter.doFilterInternal(request, response, filterChain);

            verify(SecurityContextHolder.getContext(), never()).setAuthentication(any());
            verify(filterChain).doFilter(request, response);
        }
    }
}

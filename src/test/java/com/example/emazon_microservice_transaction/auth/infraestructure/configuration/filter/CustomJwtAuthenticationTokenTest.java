package com.example.emazon_microservice_transaction.auth.infraestructure.configuration.filter;

import com.example.emazon_microservice_transaction.util.ConstantsAuth;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomJwtAuthenticationTokenTest {

    private String USER;

    @BeforeEach
    void setUp() {
        ConstantsAuth constantsAuth = new ConstantsAuth();

        USER = constantsAuth.USER;
    }

    @Test
    void shouldReturnClaimsAndAuthorities() {
        Claims claims = mock(Claims.class);
        List<GrantedAuthority> authorities = List.of(mock(GrantedAuthority.class));

        CustomJwtAuthenticationToken token = new CustomJwtAuthenticationToken(claims, authorities);

        assertEquals(claims, token.getClaims());
        assertEquals(authorities, token.getAuthorities());
        assertTrue(token.isAuthenticated());
    }

    @Test
    void shouldReturnPrincipalFromClaims() {
        Claims claims = mock(Claims.class);
        when(claims.getSubject()).thenReturn(USER);

        CustomJwtAuthenticationToken token = new CustomJwtAuthenticationToken(claims, List.of());

        assertEquals(USER, token.getPrincipal());
    }

    @Test
    void shouldReturnNullForCredentials() {
        Claims claims = mock(Claims.class);
        CustomJwtAuthenticationToken token = new CustomJwtAuthenticationToken(claims, List.of());

        assertNull(token.getCredentials());
    }
}

package com.example.emazon_microservice_transaction.auth.application.service;

import com.example.emazon_microservice_transaction.util.ConstantsAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Key;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenValidatorTest {

    private JwtTokenValidator jwtTokenValidator;

    private String STRING_KEY;
    private Date TIME;
    private Date TIME_EXPIRED;
    private Date TIME_NOT_EXPIRED;
    private String USER;
    private String ROLE;
    private String CLAIM_ROLE_PARAM;

    private Key KEY;

    @BeforeEach
    void setUp() {
        jwtTokenValidator = new JwtTokenValidator();
        ConstantsAuth constantsAuth = new ConstantsAuth();

        STRING_KEY = constantsAuth.STRING_KEY;
        TIME = constantsAuth.TIME;
        TIME_EXPIRED = constantsAuth.TIME_EXPIRED;
        TIME_NOT_EXPIRED = constantsAuth.TIME_NOT_EXPIRED;
        USER = constantsAuth.USER;
        ROLE = constantsAuth.ROLE;
        CLAIM_ROLE_PARAM = constantsAuth.CLAIM_ROLE_PARAM;

        byte[] keyBytes = Decoders.BASE64.decode(STRING_KEY);
        KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    @Test
    void shouldValidateTokenSuccessfully() {

        String token = Jwts.builder()
                .setSubject(USER)
                .claim(CLAIM_ROLE_PARAM, ROLE)
                .setExpiration(TIME)
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();


        Claims claims = jwtTokenValidator.validateToken(token);

        assertNotNull(claims);
        assertEquals(USER, claims.getSubject());
        assertEquals(ROLE, claims.get(CLAIM_ROLE_PARAM, String.class));
    }

    @Test
    void shouldReturnTrueWhenTokenIsExpired() {

        Claims claims = Jwts.claims().setExpiration(TIME_EXPIRED);
        assertTrue(jwtTokenValidator.isTokenExpired(claims));
    }

    @Test
    void shouldReturnFalseWhenTokenIsNotExpired() {

        Claims claims = Jwts.claims().setExpiration(TIME_NOT_EXPIRED);
        assertFalse(jwtTokenValidator.isTokenExpired(claims));
    }
}


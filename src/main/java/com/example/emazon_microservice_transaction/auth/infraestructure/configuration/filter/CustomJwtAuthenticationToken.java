package com.example.emazon_microservice_transaction.auth.infraestructure.configuration.filter;

import io.jsonwebtoken.Claims;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
public class CustomJwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Claims claims;

    public CustomJwtAuthenticationToken(Claims claims, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.claims = claims;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return claims.getSubject();
    }

}


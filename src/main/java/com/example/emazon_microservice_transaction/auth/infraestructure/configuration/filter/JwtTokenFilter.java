package com.example.emazon_microservice_transaction.auth.infraestructure.configuration.filter;

import com.example.emazon_microservice_transaction.auth.application.service.JwtTokenValidator;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenValidator jwtTokenValidator;
    private static final int POSITION_FINAL_OF_BEARER = 7;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(POSITION_FINAL_OF_BEARER);
            Claims claims = jwtTokenValidator.validateToken(token);
            if (claims != null && !jwtTokenValidator.isTokenExpired(claims)) {

                // Extraer roles (authorities) del token JWT
                List<GrantedAuthority> authorities = extractAuthoritiesFromClaims(claims);

                CustomJwtAuthenticationToken authentication = new CustomJwtAuthenticationToken(claims, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private List<GrantedAuthority> extractAuthoritiesFromClaims(Claims claims) {
        // Supongamos que los roles se almacenan como un claim de tipo "roles" (una lista de Strings)
        String rolesString = claims.get("role", String.class);
        if (rolesString == null || rolesString.isEmpty()) {
            return Collections.emptyList();
        }

        // Convertir la cadena de roles a una lista de GrantedAuthority
        List<String> roles = List.of(rolesString);
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}

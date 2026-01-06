package com.amjad.shopapi.sicherheit;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Filter zur Überprüfung von JWT-Tokens bei jeder Anfrage.

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest anfrage,
                                    HttpServletResponse antwort,
                                    FilterChain filterKette)
            throws ServletException, IOException {

        String authHeader = anfrage.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            if (jwtUtils.tokenGueltig(token)) {
                String benutzername = jwtUtils.benutzernameAusToken(token);

                UsernamePasswordAuthenticationToken authentifizierung =
                        new UsernamePasswordAuthenticationToken(
                                benutzername,
                                null,
                                null
                        );

                authentifizierung.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(anfrage)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentifizierung);
            }
        }

        filterKette.doFilter(anfrage, antwort);
    }
}

package com.alquilaya.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private IJwtService jwtService; 

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        int userId = -1;
        String role = null;

        // Verificar si el encabezado Authorization contiene un token Bearer
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);  // Extraer el token
            userId = jwtService.extractUserId(token,"access");  // Extraer el userId (sub) del token
            role = jwtService.extractRole(token,"access");  // Extraer el rol del token
        }

        // Proceder si el token y el rol están presentes y no hay autenticación previa
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                // Validar el token
                if (jwtService.validateToken(token, userId, "access")) {
                    // Crear un UsernamePasswordAuthenticationToken con las autoridades (roles)
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userId, null, List.of(new SimpleGrantedAuthority("ROLE_" + role)));

                    // Establecer los detalles de la autenticación
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // Establecer la autenticación en el contexto de seguridad
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // Manejo de errores
                System.out.println("Error al cargar el usuario: " + e.getMessage());
            }
        }

        // Continuar con el siguiente filtro
        filterChain.doFilter(request, response);
    }
}
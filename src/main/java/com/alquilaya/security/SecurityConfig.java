package com.alquilaya.security;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;






@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	
AuthenticationManager auth;

@Value("${cors.allowed-origins}")
private String[] allowedOrigins;
	
private final JwtAuthFilter jwtAuthFilter;

// Inyección de dependencia
public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
    this.jwtAuthFilter = jwtAuthFilter;
}

	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration conf) throws Exception {
		auth = conf.getAuthenticationManager();
		return auth;
	}
	


    /**
     * Configuración de la cadena de filtros de seguridad.
     */
    
    @Bean
	public SecurityFilterChain filterchain(HttpSecurity http) throws Exception {
    	
		http.csrf(cus->cus.disable())
		.cors(cors -> cors.configurationSource(corsConfigurationSource()))
		.authorizeHttpRequests(aut->
			aut.requestMatchers(HttpMethod.POST,"/api-courses/course").hasRole("ADMINS")
            .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
            .requestMatchers("/api/v1/customer/**").hasRole("CUSTOMER")
            .requestMatchers("/api/v1/supplier/**").hasAnyRole("SUPPLIER","CUSTOMER")
            .requestMatchers("/api/v1/user/**").hasAnyRole("ADMIN","CUSTOMER","SUPPLIER")
			.anyRequest().permitAll()
			)
		.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
    /**
     * Configuración de CORS.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(allowedOrigins));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // Encabezados permitidos
        config.setExposedHeaders(Arrays.asList("Authorization")); // Encabezados expuestos al cliente
        config.setAllowCredentials(true); // Permitir credenciales (cookies, tokens, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // Aplica a todos los endpoints
        return source;
    }

  
}

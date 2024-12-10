package com.alquilaya.security;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alquilaya.jpa.IUserJpa;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService implements IJwtService {

	 @Value("${jwt.secret}")
	    private String SECRET;
	    
	    @Value("${jwt.refresh-secret}")
	    private String REFRESH_SECRET;
	    
	  

	    @Autowired
	    IUserJpa jpaUser;

	  
	    private String createToken(Map<String, Object> claims, int userId, String role, long expirationTimeInMillis, String type) {
	        claims.put("userId", userId);
	        claims.put("role", role);
	        return Jwts.builder()
	                   .setClaims(claims)
	                   .setSubject(String.valueOf(userId))
	                   .setIssuedAt(new Date(System.currentTimeMillis()))
	                   .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis)) 
	                   .signWith(getSignKey(type), SignatureAlgorithm.HS256)  
	                   .compact();
	    }

	    // Método para verificar si un token ha expirado
	    private Boolean isTokenExpired(String token, String type) {
	        return extractExpiration(token, type).before(new Date());
	    }

	    // Obtener la clave secreta para firmar los tokens
	    private Key getSignKey(String type) {
	        String secretKey = "access".equals(type) ? SECRET : REFRESH_SECRET;
	        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
	        return Keys.hmacShaKeyFor(keyBytes);
	    }

	    // Extraer todas las claims (información) del token
	    private Claims extractAllClaims(String token, String type) {
	        return Jwts.parserBuilder()
	                   .setSigningKey(getSignKey(type))  // Pasar el tipo para seleccionar la clave correcta
	                   .build()
	                   .parseClaimsJws(token)
	                   .getBody();
	    }

	    // Método para generar un accessToken
	    @Override
	    public String generateToken(int userId, String role) {
	        Map<String, Object> claims = new HashMap<>();
	        long expirationTime = 1000 * 60 * 30;  // 30 minutos
	        return createToken(claims, userId, role, expirationTime,"access");
	    }

	    // Extraer el userId del token
	    @Override
	    public int extractUserId(String token, String type) {
	        return Integer.parseInt(extractClaim(token, Claims::getSubject, type));
	    }

	    // Extraer la expiración del token
	    @Override
	    public Date extractExpiration(String token, String type) {
	        return extractClaim(token, Claims::getExpiration, type);
	    }

	    // Método genérico para extraer cualquier claim del token
	    @Override
	    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String type) {
	        Claims claims = extractAllClaims(token, type);
	        return claimsResolver.apply(claims);
	    }

	    // Validar si el token es válido (userId coincide y no está expirado)
	    @Override
	    public boolean validateToken(String token, int userId, String type) {
	        int tokenUserId = extractUserId(token, type);
	        return (userId == tokenUserId && !isTokenExpired(token, type));
	    }

	    // Extraer el role del token
	    @Override
	    public String extractRole(String token, String type) {
	        Claims claims = extractAllClaims(token, type);
	        return claims.get("role", String.class);
	    }

	  
	    
	 // Generar un refreshToken
	    @Override
	    public String generateRefreshToken(int userId, String role) {
	        // Crear el mapa de claims
	        Map<String, Object> claims = new HashMap<>();
	        claims.put("userId", userId);
	        claims.put("role", role);

	        // Fecha de expiración: 7 días desde la fecha de creación
	        long expirationTimeMillis = 1000L * 60 * 60 * 24 * 7; // 7 días en milisegundos

	        // Crear y retornar el refreshToken
	        return createToken(claims, userId, role, expirationTimeMillis, "refresh");
	    }

	    @Override
	    public boolean isRefreshTokenExpired(String refreshToken) {
	        return isTokenExpired(refreshToken, "refresh");
	    }

}
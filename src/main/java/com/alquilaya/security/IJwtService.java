package com.alquilaya.security;

import java.util.Date;
import java.util.function.Function;


import io.jsonwebtoken.Claims;



public interface IJwtService {

    String generateToken(int userId,String role);
    String generateRefreshToken(int userId,String role);
    int  extractUserId(String token,String type);
    Date extractExpiration(String token,String type);
    String extractRole(String token,String type);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver,String type);
    boolean validateToken(String token, int userId,String type);
    boolean isRefreshTokenExpired(String refreshToken);
}

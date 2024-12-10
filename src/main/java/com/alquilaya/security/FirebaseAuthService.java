package com.alquilaya.security;

import com.alquilaya.dao.IUserDao;
import com.alquilaya.entity.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirebaseAuthService {

    @Autowired
    private IUserDao dao;
    
	@Autowired
	private IJwtService jwtService;

	public Map<String, String> verifyTokenAndSaveUser(String token) throws FirebaseAuthException {
	    try {
	        // Verifica el token y obtiene el UID
	        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
	        String firebaseUid = decodedToken.getUid();
	        String email=decodedToken.getEmail();
	        
	        // Buscar o crear el usuario
	        User user = dao.findByFirebaseUid(firebaseUid);
	        
	        if (user == null) {
	            user = new User();
	            user.setFirebaseUid(firebaseUid);
	            user.setRole("CUSTOMER"); 
	            user.setEmail(email);
	            dao.saveUser(user);  // Verifica que esto se está guardando correctamente en la base de datos
	        }

	        String role = user.getRole();
            if (role == null) {
                role = "USER";  // Si el rol no está asignado, asigna un valor por defecto
            }

            // Generar los tokens con el rol
            String accessToken = jwtService.generateToken(user.getUserId(), role);
            String refreshToken = jwtService.generateRefreshToken(user.getUserId(), role);
            
            // Devolver tokens y rol
            Map<String, String> tokens = new HashMap<>();
            tokens.put("accessToken", accessToken);
            tokens.put("refreshToken", refreshToken);
            tokens.put("role", role); // Añadir el rol en la respuesta
            return tokens;
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error al verificar el token de Firebase", e);
        }
    }
}

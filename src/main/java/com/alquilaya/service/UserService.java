package com.alquilaya.service;



import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;


import com.alquilaya.dao.IProfileDao;
import com.alquilaya.dao.IUserDao;
import com.alquilaya.dto.UserProfileDTO;
import com.alquilaya.entity.Profile;
import com.alquilaya.entity.User;
import com.alquilaya.security.IJwtService;
import com.alquilaya.util.EmailServiceImpl;


@Service
public class UserService implements IUserService {

	 @Value("${link-reset}")
	    private String LINK_RESET;
	 @Value("${link-verification}")
	    private String LINK_VERIFICATION;
	
	
	@Autowired
	IUserDao dao;

	
	@Autowired
	EmailServiceImpl emailService;
	
	@Autowired
	IProfileDao profileDao;


	@Autowired
	private IJwtService jwtService;

	
	@Override
	public String refreshAccessToken(String refreshToken) {
	    // Validar si el refreshToken es nulo o ha expirado
	    if (refreshToken == null || jwtService.isRefreshTokenExpired(refreshToken)) {
	        throw new RuntimeException("El refreshToken es inválido o ha expirado");
	    }

	    // Extraer el userId y el role desde el refreshToken
	    int userId = jwtService.extractUserId(refreshToken, "refresh");
	    String role = jwtService.extractRole(refreshToken, "refresh");

	    // Generar y devolver un nuevo accessToken
	    return jwtService.generateToken(userId, role);
	}


 
	@Override
    public UserProfileDTO loadUserDetails(int userId) {
        // Buscar al usuario
        User user = dao.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar el perfil del usuario
        Profile profile = profileDao.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado"));

        // Mapear los datos a UserRegistrationDTO
        UserProfileDTO userRegistrationDTO = new UserProfileDTO();
        userRegistrationDTO.setEmail(user.getEmail());
        userRegistrationDTO.setRole(user.getRole());
        userRegistrationDTO.setStatusDocument(user.getStatusDocument());
        
        // Setear los datos del perfil
        userRegistrationDTO.setFirstName(profile.getFirstName());
        userRegistrationDTO.setLastName(profile.getLastName());
        userRegistrationDTO.setBirthDate(profile.getBirthDate());
        userRegistrationDTO.setBio(profile.getBio());
        userRegistrationDTO.setPhoto(profile.getPhoto());
        userRegistrationDTO.setTypeDocumentId(profile.getTypeDocument().getTypeDocumentId());
        userRegistrationDTO.setDocumentNumber(profile.getDocumentNumber());

        return userRegistrationDTO;
    }

	@Override
	public List<UserProfileDTO> findAllUsers() {
		
		return dao.findAllUsers();
	}

}

package com.alquilaya.service;

import java.util.List;


import com.alquilaya.dto.UserProfileDTO;


public interface IUserService {
	
	
	String refreshAccessToken(String refreshToken);
	UserProfileDTO loadUserDetails(int userId);
	 List<UserProfileDTO> findAllUsers();
	
}

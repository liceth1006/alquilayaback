package com.alquilaya.dao;



import java.util.List;
import java.util.Optional;

import com.alquilaya.dto.UserProfileDTO;
import com.alquilaya.entity.Profile;
import com.alquilaya.entity.TypeDocument;
import com.alquilaya.entity.User;

public interface IUserDao {
	
	User findByFirebaseUid(String firebaseUid);
	
	
	
	void saveUser(User user); 
	
	Profile saveProfile(Profile profile);
	TypeDocument findTypeDocumentById(int typeDocumentId);
	User userSave(User user);
	Optional<User> findUserByEmail(String email);
	
	Optional<User> findUserById(int userId);
	Optional<User> findById(int userId);
	 List<UserProfileDTO> findAllUsers();
	 
	

}

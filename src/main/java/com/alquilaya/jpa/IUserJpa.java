package com.alquilaya.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alquilaya.dto.UserProfileDTO;
import com.alquilaya.dto.UserRegistrationDTO;
import com.alquilaya.entity.Profile;
import com.alquilaya.entity.User;

public interface IUserJpa  extends JpaRepository<User, Integer>{
	
	User findByFirebaseUid(String firebaseUid);

	@Query("SELECT new com.alquilaya.dto.UserProfileDTO(u.id, u.email, u.role, u.status, u.statusDocument, " +
            "u.createdAt, u.updatedAt, p.id, p.bio, p.birthDate, p.documentNumber, p.firstName, p.lastName, p.photo, p.typeDocument.id) " +
            "FROM User u " +
            "JOIN u.profile p")
List<UserProfileDTO> findAllUserProfiles();



	
	Optional<User> findByEmail(String email);

	    List<User> findByRole(String role);
}

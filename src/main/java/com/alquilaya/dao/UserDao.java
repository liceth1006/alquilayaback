package com.alquilaya.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.alquilaya.dto.UserProfileDTO;
import com.alquilaya.entity.Profile;
import com.alquilaya.entity.TypeDocument;
import com.alquilaya.entity.User;
import com.alquilaya.jpa.IProfileJpa;
import com.alquilaya.jpa.ITypeDocumentJpa;
import com.alquilaya.jpa.IUserJpa;
import com.alquilaya.util.EmailServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@Repository
public class UserDao implements IUserDao {

	@Autowired
	IUserJpa jpaUser;

	@Autowired
	IProfileJpa jpaProfile;


	@Autowired
	EmailServiceImpl emailService;

	@Autowired
	ITypeDocumentJpa typeDocumentJpa;

	@Override
	public User findByFirebaseUid(String firebaseUid) {

		return jpaUser.findByFirebaseUid(firebaseUid);
	}

	@Override
	public TypeDocument findTypeDocumentById(int typeDocumentId) {
		if (typeDocumentId <= 0) {
			throw new IllegalArgumentException("ID de tipo de documento inválido.");
		}
		return typeDocumentJpa.findById(typeDocumentId).orElseThrow(
				() -> new EntityNotFoundException("TypeDocument with ID " + typeDocumentId + " not found"));
	}

	@Override
	public void saveUser(User user) {
		jpaUser.save(user);
	}

	@Override
	public Profile saveProfile(Profile profile) {

		return jpaProfile.save(profile);
	}

	@Override
	public User userSave(User user) {
		return jpaUser.save(user);
	}

	@Override
	public Optional<User> findUserByEmail(String email) {
		return jpaUser.findByEmail(email);
	}

	@Override
	public Optional<User> findUserById(int userId) {
		return jpaUser.findById(userId);
	}

	@Override
	public Optional<User> findById(int userId) {
		return jpaUser.findById(userId);
	}

	@Override
	public List<UserProfileDTO> findAllUsers() {

		return jpaUser.findAllUserProfiles();
	}

}

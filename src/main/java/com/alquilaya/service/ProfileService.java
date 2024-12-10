package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IProfileDao;
import com.alquilaya.entity.Profile;

@Service
public class ProfileService implements IProfileService {

	@Autowired
	IProfileDao dao;
	
	@Override
	public Profile saveProfile(Profile profile) {
		return dao.saveProfile(profile);
	}

	@Override
	public List<Profile> findAllProfiles() {
		return dao.findAllProfiles();
	}

	@Override
	public Optional<Profile> findProfileById(Integer id) {
		return dao.findProfileById(id);
	}

	@Override
	public Optional<Profile> findProfileByUserId(Integer userId) {
		return dao.findProfileByUserId(userId);
	}
	@Override
	public boolean updateProfile(Profile profile, Integer userId) {
        // Llamamos al repositorio para actualizar el perfil
        return dao.updateProfile(profile, userId);
    }

}

package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Profile;
import com.alquilaya.jpa.IProfileJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ProfileDao  implements IProfileDao{

	@Autowired
	IProfileJpa jpa;
	
	@Override
	public Profile saveProfile(Profile profile) {
		return jpa.save(profile);
	}

	@Override
	public List<Profile> findAllProfiles() {
		return jpa.findAll();
	}

	@Override
	public Optional<Profile> findProfileById(Integer id) {
		return jpa.findById(id);
	}

	@Override
	public Optional<Profile> findProfileByUserId(Integer userId) {
		return jpa.findByUser(userId);
	}

	// Método para actualizar el perfil
	@Override
    public boolean updateProfile(Profile profile, int userId) {
        // Obtener el perfil asociado al userId
        Profile existingProfile = jpa.findByUser_userId(userId);

        if (existingProfile == null) {
            return false;  // Perfil no encontrado
        }

        // Actualizar los campos del perfil con los valores proporcionados
        existingProfile.setBio(profile.getBio());
        existingProfile.setFirstName(profile.getFirstName());
        existingProfile.setLastName(profile.getLastName());
        existingProfile.setBirthDate(profile.getBirthDate());
        // Agregar más campos si es necesario

        // Guardar el perfil actualizado en la base de datos
        jpa.save(existingProfile);

        return true;  // Actualización exitosa
    }

	@Autowired
    private IProfileJpa jpaProfile;

    @Override
    public Optional<Profile> findByUserId(int userId) {
        return jpaProfile.findByUser_UserId(userId);
    }
}

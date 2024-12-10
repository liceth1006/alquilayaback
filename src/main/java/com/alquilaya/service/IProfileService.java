package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Profile;

public interface IProfileService {
	 Profile saveProfile(Profile profile);
	 List<Profile> findAllProfiles();
	 Optional<Profile> findProfileById(Integer id);
	 Optional<Profile> findProfileByUserId(Integer userId);
	 boolean updateProfile(Profile profile, Integer userId);
}

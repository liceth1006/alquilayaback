package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Profile;

public interface IProfileDao {
	 Profile saveProfile(Profile profile);
	 List<Profile> findAllProfiles();
	 Optional<Profile> findProfileById(Integer id);
	 Optional<Profile> findProfileByUserId(Integer userId);
	 boolean updateProfile(Profile profile, int userId);
	 Optional<Profile> findByUserId(int userId);
}

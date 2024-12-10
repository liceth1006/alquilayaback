package com.alquilaya.jpa;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.alquilaya.entity.Profile;


public interface IProfileJpa  extends JpaRepository<Profile, Integer>{
	

	@Query("SELECT p FROM Profile p WHERE p.user.userId = ?1")
	Optional<Profile> findByUser (Integer userId);
	Profile findByUser_userId(int userId);
	 Optional<Profile> findByUser_UserId(int userId);
}

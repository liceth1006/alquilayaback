package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Phone;
import com.alquilaya.jpa.IPhoneJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class PhoneDao implements IPhoneDao{

	
	@Autowired
	IPhoneJpa jpa;
	
	@Override
	public Phone savePhone(Phone phone) {
		return jpa.save(phone);
	}

	@Override
	public List<Phone> findAllPhones() {
		return jpa.findAll();
	}

	@Override
	public Optional<Phone> findPhoneById(Integer id) {
		 return jpa.findById(id);
	}

	@Override
	public List<Phone> findPhonesByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Phone updatePhone(Phone phone, Integer id) {
		if (jpa.existsById(id)) {
			phone.setPhoneId(id);  
            return jpa.save(phone);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

}

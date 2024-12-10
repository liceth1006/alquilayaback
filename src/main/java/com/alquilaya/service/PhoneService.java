package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.IPhoneDao;
import com.alquilaya.entity.Phone;

@Service
public class PhoneService implements IPhoneService {

	@Autowired
	IPhoneDao dao;
	@Override
	public Phone savePhone(Phone phone) {
		
		return dao.savePhone(phone);
	}

	@Override
	public List<Phone> findAllPhones() {
		
		return dao.findAllPhones();
	}

	@Override
	public Optional<Phone> findPhoneById(Integer id) {
		
		return dao.findPhoneById(id);
	}

	@Override
	public List<Phone> findPhonesByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Phone updatePhone(Phone phone, Integer id) {
		
		return dao.updatePhone(phone, id);
	}

}

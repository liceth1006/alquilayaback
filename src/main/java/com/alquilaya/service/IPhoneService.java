package com.alquilaya.service;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Phone;

public interface IPhoneService {
	Phone savePhone(Phone phone);
    List<Phone> findAllPhones();
    Optional<Phone> findPhoneById(Integer id);
    List<Phone> findPhonesByUserId(Integer userId);
    Phone updatePhone(Phone phone, Integer id);
}

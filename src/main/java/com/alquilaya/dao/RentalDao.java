package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Rental;
import com.alquilaya.jpa.IRentalJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class RentalDao implements IRentalDao{

	@Autowired
	IRentalJpa jpa;
	@Override
	public Rental saveRental(Rental rental) {
		return jpa.save(rental);
	}

	@Override
	public List<Rental> findAllRentals() {
		return jpa.findAll();
	}

	@Override
	public Optional<Rental> findRentalById(Integer id) {
		return jpa.findById(id);
	}

	@Override
    public List<Rental> findBySupplierId(Integer supplierId) {
        return jpa.findBySupplierId(supplierId);
    }

    @Override
    public List<Rental> findRentalsByProductId(Integer productId) {
        return jpa.findByProductId(productId);
    }
    
	@Override
	public Rental updateRental(Rental rental, Integer id) {
		if (jpa.existsById(id)) {
			rental.setRentalId(id);  
            return jpa.save(rental);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }

}

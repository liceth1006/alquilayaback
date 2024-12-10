package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alquilaya.entity.Commission;
import com.alquilaya.jpa.ICommissionJpa;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class CommissionDao implements ICommissionDao {

	@Autowired
	ICommissionJpa jpa;
	
	@Override
	public Commission saveCommission(Commission commission) {
		return jpa.save(commission);
	}

	@Override
	public List<Commission> findAllCommissions() {
		return jpa.findAll();
	}

	@Override
	public Optional<Commission> findCommissionById(Integer id) {
		 return jpa.findById(id);
	}

	@Override
	public Commission updateCommission(Commission commission, Integer id) {
		if (jpa.existsById(id)) {
			commission.setCommissionId(id);  
            return jpa.save(commission);  
        }
        throw new EntityNotFoundException("Address with ID " + id + " not found");  
    }
	

	@Override
	public void deleteCommission(Integer id) {
		// TODO Auto-generated method stub
		
	}

}

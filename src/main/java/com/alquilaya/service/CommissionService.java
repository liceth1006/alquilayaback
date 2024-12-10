package com.alquilaya.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alquilaya.dao.ICommissionDao;
import com.alquilaya.entity.Commission;

@Service
public class CommissionService implements ICommissionService {

	
	@Autowired
	ICommissionDao dao;
	@Override
	public Commission saveCommission(Commission commission) {
		// TODO Auto-generated method stub
		return  dao.saveCommission(commission);
	}

	@Override
	public List<Commission> findAllCommissions() {
		
		return dao.findAllCommissions();
	}

	@Override
	public Optional<Commission> findCommissionById(Integer id) {
		
		return dao.findCommissionById(id);
	}

	@Override
	public Commission updateCommission(Commission commission, Integer id) {
		
		return dao.updateCommission(commission, id);
	}

	@Override
	public BigDecimal calculateCommission(BigDecimal amount, BigDecimal rate) {
		
		return null;
	}

}

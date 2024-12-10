package com.alquilaya.dao;

import java.util.List;
import java.util.Optional;

import com.alquilaya.entity.Commission;

public interface ICommissionDao {
	 Commission saveCommission(Commission commission);
	    List<Commission> findAllCommissions();
	    Optional<Commission> findCommissionById(Integer id);
	    Commission updateCommission(Commission commission, Integer id);
	    void deleteCommission(Integer id);
}

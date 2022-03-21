package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entites.concretes.AdditionalService;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

	Set<AdditionalService> findById(int additionalServiceId);
	
	//@Query("select a from AdditionalService a where a.additionalServiceName =")
    AdditionalService findByAdditionalServiceName(String additionalServiceName);
}

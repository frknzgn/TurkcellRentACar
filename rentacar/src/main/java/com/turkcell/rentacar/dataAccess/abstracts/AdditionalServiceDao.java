package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entites.concretes.AdditionalService;

public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

	AdditionalService getByAdditionalServiceId(int additionalServiceId);
	
	//@Query("select a from AdditionalService a where a.additionalServiceName =")
    AdditionalService findByAdditionalServiceName(String additionalServiceName);
}

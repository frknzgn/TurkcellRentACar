package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.AdditionalService;

@Repository
public interface AdditionalServiceDao extends JpaRepository<AdditionalService, Integer> {

	 boolean existsByAdditionalServiceId(int additionalServiceId);

	 boolean existsByAdditionalServiceName(String additionalServiceName);
	
}

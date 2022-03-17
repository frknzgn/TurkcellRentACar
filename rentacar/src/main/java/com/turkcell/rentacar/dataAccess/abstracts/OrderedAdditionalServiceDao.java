package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entites.concretes.OrderedAdditionalService;

public interface OrderedAdditionalServiceDao extends JpaRepository<OrderedAdditionalService, Integer>{

	Set<OrderedAdditionalService> findByRental_rentalId(int rentalId);
	
}

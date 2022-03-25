package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.turkcell.rentacar.entites.concretes.CardDetail;

public interface CardDetailDao extends JpaRepository<CardDetail, Integer>{
	
	List<CardDetail> getByCustomer_CustomerId(int customerId);
	
}

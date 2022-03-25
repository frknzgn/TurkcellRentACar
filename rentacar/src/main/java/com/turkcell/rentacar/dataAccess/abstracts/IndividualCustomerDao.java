package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.IndividualCustomer;

@Repository
public interface IndividualCustomerDao extends JpaRepository<IndividualCustomer, Integer>{
	
	IndividualCustomer getByCustomerId(int CustomerId);
	
}
package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.Rental;

@Repository
public interface RentalDao extends JpaRepository<Rental, Integer>{
	
	List<Rental> getByCar_CarId(int carId);
	
	List<Rental> findByCustomer_CustomerId(int customerId);

}

package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.OrderedAdditionalService;

@Repository
public interface OrderedAdditionalServiceDao extends JpaRepository<OrderedAdditionalService, Integer>{

	List<OrderedAdditionalService> findAllByOrderedAdditionalServiceId(int orderedAdditionalId);

    List<OrderedAdditionalService> getByRentalRentalId(int rentalId);
	
}

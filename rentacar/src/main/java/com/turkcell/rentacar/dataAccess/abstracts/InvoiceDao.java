package com.turkcell.rentacar.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.Invoice;

@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
	
	    List<Invoice> getByCustomerUserId(int customerId);

	    List<Invoice> getByCreatingDateBetween(LocalDate startingDate, LocalDate endingDate);

	    boolean existsByCustomer_UserId(int customerId);
	
}

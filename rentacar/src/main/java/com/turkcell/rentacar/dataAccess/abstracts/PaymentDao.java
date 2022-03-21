package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.rentacar.entites.concretes.Payment;

@Transactional
@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {
	
	Payment getPaymentById(int paymentId);
	Payment getPaymentByOrderedAdditionalServiceId(int orderedAdditionalServiceId);
	
}

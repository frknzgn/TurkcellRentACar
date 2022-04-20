package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.entites.concretes.Customer;

public interface CustomerService {
	
	Customer getById(int customerId);
	public void checkIfCustomerIdExist(int customerId);
	public void checkIfEmailExist(String email);
	
}

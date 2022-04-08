package com.turkcell.rentacar.business.abstracts;

public interface CustomerService {
	
	public void checkIfCustomerIdExist(int customerId);
	public void checkIfEmailExist(String email);
	
}

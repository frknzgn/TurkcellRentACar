package com.turkcell.rentacar.business.abstracts;

public interface CustomerService {
	
	public void checkEmailExist(String email);
	public void checkCustomerExist(String uniqueId);
	public void checkCustomerNotExist(int customerId);
}

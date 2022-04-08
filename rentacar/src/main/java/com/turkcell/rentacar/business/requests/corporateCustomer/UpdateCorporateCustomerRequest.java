package com.turkcell.rentacar.business.requests.corporateCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCorporateCustomerRequest {
	
	private int customerId;
	private String email;
    private String password;
	private String taxNumber;
	private String companyName;
	
}
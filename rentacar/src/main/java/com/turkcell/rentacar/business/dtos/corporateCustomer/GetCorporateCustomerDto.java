package com.turkcell.rentacar.business.dtos.corporateCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCorporateCustomerDto {
	
		private int customerId;
	    private String email;
	    private String taxNumber;
		private String companyName;
		
}


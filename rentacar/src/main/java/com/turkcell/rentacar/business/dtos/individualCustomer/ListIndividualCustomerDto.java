package com.turkcell.rentacar.business.dtos.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListIndividualCustomerDto {
	
	private int customerId;
    private String email;
	private String individualCustomerFirstName;
	private String individualCustomerLastName;
	private Long nationalityId;
	
}
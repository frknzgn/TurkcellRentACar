package com.turkcell.rentacar.business.requests.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	
	private int individualCustomerId;
    private String email;
    private String password;
    private String firstName;
	private String lastName;
	private String individualCustomerNationalityId;
}
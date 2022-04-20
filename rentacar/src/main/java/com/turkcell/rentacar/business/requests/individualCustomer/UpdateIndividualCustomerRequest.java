package com.turkcell.rentacar.business.requests.individualCustomer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateIndividualCustomerRequest {
	
	private int customerId;
	
	
    private String nationalityId;
	
    @NotNull
    private String firstName;
    
    @NotNull
    private String lastName;
    
    @NotNull
    @Email
    private String Email;
    
    @NotNull
    private String password;
}
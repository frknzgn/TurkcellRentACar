package com.turkcell.rentacar.business.requests.corporateCustomer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	
	@NotNull
    private String taxNumber;
    @NotNull
    private String companyName;

    @NotNull
    @Email
    private String Email;
    
    @NotNull
    private String password;
	
}


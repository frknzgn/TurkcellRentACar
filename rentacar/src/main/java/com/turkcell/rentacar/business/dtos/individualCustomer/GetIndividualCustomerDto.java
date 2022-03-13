package com.turkcell.rentacar.business.dtos.individualCustomer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetIndividualCustomerDto {
	
	private int id;
    private String email;
	private String firstName;
	private String LastName;
	
}

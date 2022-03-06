package com.turkcell.rentacar.business.requests.customer;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerRequest {
	
	private int id;
    private String email;
    private String password;

}

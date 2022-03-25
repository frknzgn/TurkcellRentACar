package com.turkcell.rentacar.business.dtos.customer;

import java.util.List;

import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCustomerDto {
	
	private int customerId;  
    private String email;
    private String password;
    private List<ListRentalDto> rentals;
    
}

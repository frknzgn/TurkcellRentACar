package com.turkcell.rentacar.business.dtos.customer;



import java.util.List;

import com.turkcell.rentacar.entites.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCustomerDto {
	
	private int id;  
    private String email;
    private String password;
    private List<Rental> rentals;
}

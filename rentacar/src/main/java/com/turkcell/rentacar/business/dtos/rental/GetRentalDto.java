package com.turkcell.rentacar.business.dtos.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRentalDto {
	
	private int rentalId;
	private int carId;
	private int customerId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	
}

package com.turkcell.rentacar.business.requests.rental;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	private int rentalId;
	
	@NotNull
	@Positive
	private int carId;
	
	@NotNull
	private int customerId;

	private LocalDate rentDate;
	
	private LocalDate dateOfReceipt;
	
	 @NotNull
	 private int rentCityId;
	 
	 @NotNull
	 private int rentReturnCityId;
	
}
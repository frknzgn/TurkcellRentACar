package com.turkcell.rentacar.business.requests.rental;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalRequest {
	
	@NotNull
	@Min(0)
	private int id;
	
	@NotNull
	@Min(0)
	private int carId;
	
	@NotNull
	@Min(0)
	private int customerId;
	
	@NotNull
	private LocalDate rentDate;
	
	@NotNull
	private LocalDate returnDate;
	
}
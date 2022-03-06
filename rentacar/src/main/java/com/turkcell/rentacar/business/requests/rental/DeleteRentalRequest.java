package com.turkcell.rentacar.business.requests.rental;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRentalRequest {
	
	@NotNull
	@Min(0)
	private int id;
	
	
}
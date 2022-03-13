package com.turkcell.rentacar.business.requests.rental;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.turkcell.rentacar.business.dtos.additionalService.AdditionalServiceIdDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	
	
	@NotNull
	@Min(0)
	private int carId;
	
	@NotNull
	@Min(0)
	private int userId;
	
	@NotNull
	private LocalDate rentDate;
	
	private LocalDate returnDate;
	
	private List<AdditionalServiceIdDto> additionalServicesIds;
	
	private double totalPrice;
	
}

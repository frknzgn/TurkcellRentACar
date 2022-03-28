package com.turkcell.rentacar.business.dtos.rental;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.additionalService.ListAdditionalServiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
	
	private int rentalId;
	private int customerId;
	private LocalDate rentDate;
	private LocalDate returnDate;
	private List<ListAdditionalServiceDto> additionalServices;
	private double totalPrice;
	
}

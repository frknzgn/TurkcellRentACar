package com.turkcell.rentacar.business.requests.rental;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.turkcell.rentacar.business.requests.orderedadditionalservice.CreateOrderedAdditionalServiceRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {
	
	@NotNull
	@Positive
	private int carId;
	
	@NotNull
	@Positive
	private int customerId;
	
	@NotNull
	private LocalDate rentDate;
	
	private LocalDate rentReturnDate;
	
	private Set<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests;
	
	private double rentTotalPrice;
	
	
}

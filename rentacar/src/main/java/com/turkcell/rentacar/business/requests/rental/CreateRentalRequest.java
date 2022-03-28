package com.turkcell.rentacar.business.requests.rental;

import java.time.LocalDate;
import java.util.List;

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
	
	private LocalDate rentDate;
	
	private LocalDate rentReturnDate;
	
	private List<CreateOrderedAdditionalServiceRequest> createOrderedAdditionalServiceRequests;
	
	
}

package com.turkcell.rentacar.business.requests.orderedadditionalservice;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalServiceRequest {
	
	 @NotNull
	 @Positive
	 private int additionalServiceId;

	 @Positive
	 private int quantity;
	 
	 private int rentalId;

	 
}


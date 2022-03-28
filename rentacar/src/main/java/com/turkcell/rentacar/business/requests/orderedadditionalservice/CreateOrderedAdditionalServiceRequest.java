package com.turkcell.rentacar.business.requests.orderedadditionalservice;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalServiceRequest {
	
	 @NotNull
	 private int additionalServiceId;

	 private int quantity;

	 
}


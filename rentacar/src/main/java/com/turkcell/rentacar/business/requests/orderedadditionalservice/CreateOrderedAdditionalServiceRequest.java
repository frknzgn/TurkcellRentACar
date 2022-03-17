package com.turkcell.rentacar.business.requests.orderedadditionalservice;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateOrderedAdditionalServiceRequest {
	
	 @NotNull
	 @Positive
	 private int additionalServiceId;

	 @Positive
	 private int quantity;
	    
}

package com.turkcell.rentacar.business.requests.orderedadditionalservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderedAdditionalServiceRequest {
	 
	private int rentalId;
	
	private int additionalServiceId;
 
}


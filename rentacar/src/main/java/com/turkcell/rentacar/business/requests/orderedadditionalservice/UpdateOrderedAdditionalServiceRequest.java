package com.turkcell.rentacar.business.requests.orderedadditionalservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalServiceRequest {
	
	private int orderedAdditionalServiceId;
	
	private int rentalId;
	
	private int additionalServiceId;
 
}
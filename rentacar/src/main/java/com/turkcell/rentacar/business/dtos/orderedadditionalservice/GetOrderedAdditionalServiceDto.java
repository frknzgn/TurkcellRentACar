package com.turkcell.rentacar.business.dtos.orderedadditionalservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetOrderedAdditionalServiceDto {
	
	private int orderedAdditionalServiceId;
	
	private int rental_Customer_CustomerId;
	 
    private String additionalServiceName;

    
}


package com.turkcell.rentacar.business.dtos.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdditionalServiceDto {
	
	private int id;
	private String additionalServiceName;	
	private String additionalServiceDescription;
	private double additionalServicePrice;

	
}

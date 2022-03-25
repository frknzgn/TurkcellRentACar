package com.turkcell.rentacar.business.dtos.additionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAdditionalServiceDto {
	
	private int additionalServiceId;
	private String additionalServiceName;
	private String additionalServiceDescription;
	private double additionalServicePrice;

	
}

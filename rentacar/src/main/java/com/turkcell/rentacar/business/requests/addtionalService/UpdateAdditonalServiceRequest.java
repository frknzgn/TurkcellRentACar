package com.turkcell.rentacar.business.requests.addtionalService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditonalServiceRequest {
	
	private int additionalServiceId;
	private String additionalServiceName;
	private String additionalServiceDescription;
	private double additionalServicePrice;
	
	
}
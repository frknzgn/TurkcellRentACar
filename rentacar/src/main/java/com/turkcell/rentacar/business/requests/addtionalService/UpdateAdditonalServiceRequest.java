package com.turkcell.rentacar.business.requests.addtionalService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditonalServiceRequest {
	
	private int additionalServiceId;
	
	@NotNull
	private String additionalServiceName;
	
	@NotNull
    @Min(1)
	private double additionalServicePrice;
	
	
}
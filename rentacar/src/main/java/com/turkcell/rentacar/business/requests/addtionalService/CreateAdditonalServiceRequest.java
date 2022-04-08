package com.turkcell.rentacar.business.requests.addtionalService;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdditonalServiceRequest {
	
	@NotNull
    @Min(1)
	private String additionalServiceName;
	
	@NotNull
	private double additionalServicePrice;
	
	
}

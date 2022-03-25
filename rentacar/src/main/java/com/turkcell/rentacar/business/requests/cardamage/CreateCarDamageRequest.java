	package com.turkcell.rentacar.business.requests.cardamage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
	
	@NotNull
	private int carId;
	
	@Size(min = 2,max =250)
	private String carDamageInfo;

}


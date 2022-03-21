package com.turkcell.rentacar.business.requests.cardamage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarDamageRequest {
	
	@NotNull
	@Positive
	private int carId;
	
	@Size(min = 2,max =20)
	private String carDamageInfo;

}


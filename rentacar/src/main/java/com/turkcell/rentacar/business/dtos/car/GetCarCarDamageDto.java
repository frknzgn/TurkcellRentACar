package com.turkcell.rentacar.business.dtos.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarCarDamageDto {
	
	private int carId;
	private String description;
	private String brandName;
	
}
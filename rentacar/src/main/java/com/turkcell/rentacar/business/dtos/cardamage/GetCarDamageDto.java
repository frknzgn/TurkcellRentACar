package com.turkcell.rentacar.business.dtos.cardamage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarDamageDto {
	
	private int carDamageId;
	
	private String carDamageInfo;
	
}

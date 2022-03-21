package com.turkcell.rentacar.business.dtos.cardamage;

import com.turkcell.rentacar.business.dtos.car.GetCarDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDamageDto {
	
	private int carDamageId;
	
	private String carDamageInfo;
	
	private GetCarDto car;
}
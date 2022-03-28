package com.turkcell.rentacar.business.dtos.car;

import java.util.List;
import java.util.Set;

import com.turkcell.rentacar.business.dtos.cardamage.ListCarCarDamageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarDto {
	
	private int carId;
	private double dailyPrice;
	private int modelYear;
	private String description;
	private String brandName;
	private String colorName;
	private List<ListCarCarDamageDto> carDamages;
	
}

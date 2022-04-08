package com.turkcell.rentacar.business.requests.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarRequest {

	private int carId;
	private int modelYear;
	private int brandId;	
	private int colorId;
	private int milage;

	private String description;	
		
	private double dailyPrice;
	
}
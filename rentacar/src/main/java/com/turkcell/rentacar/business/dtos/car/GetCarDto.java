package com.turkcell.rentacar.business.dtos.car;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarDto {
	
	private int carId;
	
	private int modelYear;
	
	private double dailyPrice;
	
	private double milage;
	
	private String description;
	
	private String brandName;
	
	private String colorName;
	

	
}

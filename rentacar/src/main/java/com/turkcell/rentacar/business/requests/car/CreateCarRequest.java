package com.turkcell.rentacar.business.requests.car;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarRequest {
	
	@NotNull
	@Min(0)	
	private double dailyPrice;
	
	@NotNull
	@Min(0)
	@Max(2022)
	private int modelYear;
	
	@NotNull
	@Size(min=2,max=250)
	private String description;
	
	@NotNull
	private int brandId;
	
	@NotNull
	private int colorId;
	
}
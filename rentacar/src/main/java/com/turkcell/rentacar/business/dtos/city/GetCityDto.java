package com.turkcell.rentacar.business.dtos.city;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityDto {
	
	@NotNull
	@Positive
	private int cityId;
	
	@NotNull
	private String cityName;

}

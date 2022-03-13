package com.turkcell.rentacar.business.requests.city;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCityRequest {

	private int cityId;
	private String name;

}
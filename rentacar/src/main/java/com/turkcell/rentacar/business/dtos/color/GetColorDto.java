package com.turkcell.rentacar.business.dtos.color;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetColorDto {
	
	@Positive
	@NotNull
	private int colorId;
	
	@NotNull
	private String colorName;
}
package com.turkcell.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ListColorDto {
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class ListProductDto {
		private int colortId;
		private String colorctName;
		
		
	}
}

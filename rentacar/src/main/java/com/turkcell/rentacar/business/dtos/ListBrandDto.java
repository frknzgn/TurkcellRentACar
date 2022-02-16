package com.turkcell.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ListBrandDto {
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class ListProductDto {
		private int brandId;
		private String brandName;
		
	}
}

package com.turkcell.rentacar.business.requests.carDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCardDetailRequest {
	
	private int carDetailId;
	
}

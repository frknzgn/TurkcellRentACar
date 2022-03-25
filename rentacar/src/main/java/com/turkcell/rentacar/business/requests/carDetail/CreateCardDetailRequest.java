package com.turkcell.rentacar.business.requests.carDetail;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardDetailRequest {
	
	private String cardHolder;
	private String cardNo;
	private String cardCvv;
	private LocalDate carExpirationDate;
	private int customerCustomerId;
	
}


package com.turkcell.rentacar.business.dtos.cardDetail;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCardDetailDto {
	
	private int carDetailId;
	
	private String cardHolder;
	
	private String cardNo;
	
	private String cardCvv;
	
	private LocalDate cardExpirationDate;
	
	private int customer_CustomerId; 
	
}
package com.turkcell.rentacar.business.dtos.cardDetail;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCardDetailDto {
	
	private int carDetailId;
	
	private String cardHolder;
	
	private String cardNo;
	
	private String cardCvv;
	
	private LocalDate carExpirationDate;
	
	private int customerId; 
	
}

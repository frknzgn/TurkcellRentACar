package com.turkcell.rentacar.business.requests.carDetail;

import java.time.LocalDate;

import com.turkcell.rentacar.entites.concretes.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCardDetailRequest {
	
	private int cardDetailId;
	private String cardHolder;
	private String cardNo;
	private String cardCvv;
	private LocalDate carExpirationDate;
	private Customer customer;
	
}
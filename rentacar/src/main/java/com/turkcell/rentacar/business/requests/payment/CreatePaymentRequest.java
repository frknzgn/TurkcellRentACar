package com.turkcell.rentacar.business.requests.payment;

import java.util.List;

import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;
import com.turkcell.rentacar.business.requests.rental.CreateRentalRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	
	private CreateRentalRequest rentalRequest;

	private CreateCardDetailForPaymentRequest createCardDetailForPaymentRequest;
	
	private List<Integer> additionalServiceList;
	
	private boolean saveCreditCard;
	
}



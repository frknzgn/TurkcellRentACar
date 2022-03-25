package com.turkcell.rentacar.business.adapters;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.outServices.VakifBankManager;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

@Primary
@Service
public class VakifBankServiceAdapter implements PosService {
	
	@Override 
	public Result payment(CreatePaymentRequest createPaymentRequest) {
		
		VakifBankManager vakifBankManager = new VakifBankManager();
		return vakifBankManager.doPayment(createPaymentRequest.getCardNumber(), 
											String.valueOf(createPaymentRequest.getCardCvvNumber()), 
																	createPaymentRequest.getCardOwnerName());
		
	}
}
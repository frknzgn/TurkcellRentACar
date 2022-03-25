package com.turkcell.rentacar.business.adapters;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.outServices.ZiraatBankManager;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

@Service
public class ZiraatBankServiceAdapter implements PosService {
	
	@Override 
	public Result payment(CreatePaymentRequest createPaymentRequest) {
		
		ZiraatBankManager ziraatBankManager = new ZiraatBankManager();
		return ziraatBankManager.makePayment(createPaymentRequest.getCardNumber(), createPaymentRequest.getCardOwnerName() , String.valueOf(createPaymentRequest.getCardCvvNumber()));
		
	}
}

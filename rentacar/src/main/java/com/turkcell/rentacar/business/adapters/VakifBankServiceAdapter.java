package com.turkcell.rentacar.business.adapters;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.outServices.VakifBankManager;
import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

@Primary
@Service
public class VakifBankServiceAdapter implements PosService {
	
	@Override 
	public Result payment(CreateCardDetailForPaymentRequest cardDetailForPaymentRequest,double totalFee) {
		
		VakifBankManager vakifBankManager = new VakifBankManager();
		return vakifBankManager.doPayment(cardDetailForPaymentRequest.getCardNo(), 
											cardDetailForPaymentRequest.getCardCvv(), 
												cardDetailForPaymentRequest.getCardHolder(),1200);
		
	}


}
package com.turkcell.rentacar.business.adapters;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.outServices.ZiraatBankManager;
import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

@Service
public class ZiraatBankServiceAdapter implements PosService {
	
	@Override 
	public Result payment(CreateCardDetailForPaymentRequest cardDetailForPaymentRequest,double totalFee) {
		
		ZiraatBankManager ziraatBankManager = new ZiraatBankManager();
		
		return ziraatBankManager.makePayment(cardDetailForPaymentRequest.getCardNo(),
												cardDetailForPaymentRequest.getCardHolder() ,
													cardDetailForPaymentRequest.getCardCvv(),500);
		
	}
}

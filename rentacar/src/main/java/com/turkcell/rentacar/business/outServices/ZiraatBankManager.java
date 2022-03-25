package com.turkcell.rentacar.business.outServices;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;


@Service
public class ZiraatBankManager {
	
	public Result makePayment( String cardHolder,String cardNumber, String cardCvvCode) {

		return new SuccessResult("ZiraatBank ile ödendi.");
		
	}

}

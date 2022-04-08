package com.turkcell.rentacar.business.outServices;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;


@Service
public class VakifBankManager{

	public Result doPayment(String cardNumber, String cardCvvCode, String cardHolder,double totalFee) {

		return new SuccessResult("Vakif Bank ile ödendi.");
		
	}

}
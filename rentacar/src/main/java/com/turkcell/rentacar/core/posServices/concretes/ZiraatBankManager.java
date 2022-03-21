package com.turkcell.rentacar.core.posServices.concretes;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.core.posServices.abstracts.ZiraatBankPosService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;

@Service
public class ZiraatBankManager implements ZiraatBankPosService{

	@Override
	public Result ziraatBankService(String cardNumber, String cardCvvCode, String cardHolder) {
		
		return new SuccessResult("Ziraat Bank ile ödendi.");
		
	}

}

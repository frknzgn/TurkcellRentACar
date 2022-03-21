package com.turkcell.rentacar.core.posServices.concretes;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.core.posServices.abstracts.VakifBankPosService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;

@Service
public class VakifBankManager implements VakifBankPosService{

	@Override
	public Result vakifBankService(String cardNumber, String cardCvvCode, String cardHolder) {

		return new SuccessResult("Ziraat Bank ile ödendi.");
		
	}

}
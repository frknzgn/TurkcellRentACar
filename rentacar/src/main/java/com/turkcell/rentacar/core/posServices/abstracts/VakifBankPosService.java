package com.turkcell.rentacar.core.posServices.abstracts;

import com.turkcell.rentacar.core.utilities.results.Result;

public interface VakifBankPosService {
	
	Result vakifBankService(String cardNumber, String cardCvvCode,String cardHolder);
	
}
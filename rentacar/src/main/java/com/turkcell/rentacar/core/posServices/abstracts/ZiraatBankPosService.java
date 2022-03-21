package com.turkcell.rentacar.core.posServices.abstracts;

import com.turkcell.rentacar.core.utilities.results.Result;

public interface ZiraatBankPosService {
	
	Result ziraatBankService(String cardNumber, String cardCvvCode,String cardHolder);
	
}
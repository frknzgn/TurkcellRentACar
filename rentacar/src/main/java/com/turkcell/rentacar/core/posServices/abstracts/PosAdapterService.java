package com.turkcell.rentacar.core.posServices.abstracts;

import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface PosAdapterService {
	
	public Result payment(CreatePaymentRequest createPaymentRequest);
	
}	

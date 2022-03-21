package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface PaymentService {
	
	public Result add(CreatePaymentRequest createPaymentRequest);
	
}

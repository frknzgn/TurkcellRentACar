package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface PosService {
	
	public Result payment(CreateCardDetailForPaymentRequest cardDetailForPaymentRequest,double totalFee);
	
}

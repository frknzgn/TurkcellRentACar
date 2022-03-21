package com.turkcell.rentacar.core.posServices.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.posServices.abstracts.PosAdapterService;
import com.turkcell.rentacar.core.posServices.abstracts.VakifBankPosService;
import com.turkcell.rentacar.core.posServices.abstracts.ZiraatBankPosService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;

@Service
public class PosAdapterManager implements PosAdapterService {
	
	PaymentService paymentService;
	VakifBankPosService vakifBankPosService;
	ZiraatBankPosService ziraatBankPosService;
	
	@Autowired
	public PosAdapterManager(PaymentService paymentService, VakifBankPosService vakifBankPosService,ZiraatBankPosService ziraatBankPosService) {
		
		this.paymentService = paymentService;
		this.vakifBankPosService = vakifBankPosService;
		this.ziraatBankPosService = ziraatBankPosService;
		
	}
	
	@Override
	public Result payment(CreatePaymentRequest createPaymentRequest) {
		
		String last4digits = createPaymentRequest.getCardNumber().substring(12);
		
		if(last4digits.equals("5002")) {
			
			ziraatBankPosService.ziraatBankService(createPaymentRequest.getCardNumber(),String.valueOf(createPaymentRequest.getCardCvvNumber()),createPaymentRequest.getCardOwnerName());
			return new SuccessResult("Ziraat Bank");
			
		}
		else if (last4digits.equals("5003")) {
			
			vakifBankPosService.vakifBankService(createPaymentRequest.getCardNumber(),String.valueOf(createPaymentRequest.getCardCvvNumber()),createPaymentRequest.getCardOwnerName());
			return new SuccessResult("VakıfBank");
		
		}
		else {
			
			throw new BusinessException("Ödeme başarısız.");
			
		}
	}

}

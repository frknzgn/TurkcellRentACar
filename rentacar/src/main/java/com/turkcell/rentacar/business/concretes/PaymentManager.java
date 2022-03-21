package com.turkcell.rentacar.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.posServices.abstracts.PosAdapterService;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entites.concretes.Payment;

@Service
public class PaymentManager implements PaymentService {

	PosAdapterService posAdapterService;
	ModelMapperService modelMapperService;
	PaymentDao paymentDao;
	
	@Autowired
	public PaymentManager(@Lazy PosAdapterService posAdapterService, ModelMapperService modelMapperService, PaymentDao paymentDao) {
		
		this.posAdapterService = posAdapterService;
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
	}

	@Override
	@Transactional
	public Result add(CreatePaymentRequest createPaymentRequest) {
		
		toSendPosAdapterService(createPaymentRequest);
	
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		checkIfOrderedAdditionalServiceId(createPaymentRequest.getOrderedAdditionalServiceId());
		
		this.paymentDao.save(payment);
		
		return new SuccessResult("Ödeme başarılı.");
		
	}
	
	private void toSendPosAdapterService(CreatePaymentRequest createPaymentRequest) {
		
		this.posAdapterService.payment(createPaymentRequest);
		
	}
	
	private boolean checkIfOrderedAdditionalServiceId(int orderedAdditionalServiceId) {
		
		if (this.paymentDao.getPaymentByOrderedAdditionalServiceId(orderedAdditionalServiceId) == null) {
		
			return true;
		}
		
		throw new BusinessException("Bu kiralamanın ödemesi yapılmıştır.");
	}

}

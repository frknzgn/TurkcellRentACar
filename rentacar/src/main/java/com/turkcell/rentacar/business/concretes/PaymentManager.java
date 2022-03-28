package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.PosService;
import com.turkcell.rentacar.business.dtos.payment.GetPaymentDto;
import com.turkcell.rentacar.business.dtos.payment.ListPaymentDto;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.PaymentDao;
import com.turkcell.rentacar.entites.concretes.Payment;

@Service
public class PaymentManager implements PaymentService {

	PosService posService;
	ModelMapperService modelMapperService;
	PaymentDao paymentDao;
	
	@Autowired
	public PaymentManager(PosService posService, ModelMapperService modelMapperService, PaymentDao paymentDao) {
		
		this.posService = posService;
		this.modelMapperService = modelMapperService;
		this.paymentDao = paymentDao;
	}

	@Override
	public Result add(CreatePaymentRequest createPaymentRequest) {
		
		toSendPosAdapterService(createPaymentRequest);
	
		Payment payment = this.modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
		
		checkIfOrderedAdditionalServiceId(createPaymentRequest.getOrderedAdditionalServiceId());
		
		this.paymentDao.save(payment);
		
		return new SuccessResult(Messages.PAYMENT_ADDED);
		
	}
	
	
	@Override
	public DataResult<List<ListPaymentDto>> getAll() {
		
		var result = this.paymentDao.findAll();
		List<ListPaymentDto> response = result.stream().
				map(payment -> this.modelMapperService.forDto().
						map(payment, ListPaymentDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListPaymentDto>>(response,Messages.PAYMENT_GETALL);
	}
	
	@Override
	public DataResult<GetPaymentDto> getById(int paymentId) {

		checkPaymentExist(paymentId);
		
		Payment result = this.paymentDao.getById(paymentId);
		GetPaymentDto response = this.modelMapperService.forDto().map(result, GetPaymentDto.class);
		
		return new SuccessDataResult<GetPaymentDto>(response,Messages.PAYMENT_GETBY_ID);
		
	}
	
	
	private void toSendPosAdapterService(CreatePaymentRequest createPaymentRequest) {
		
		this.posService.payment(createPaymentRequest);
		
	}
	
	private boolean checkIfOrderedAdditionalServiceId(int orderedAdditionalServiceId) {
		
		if (this.paymentDao.getPaymentByOrderedAdditionalServiceId(orderedAdditionalServiceId) == null) {
		
			return true;
		}
		
		throw new BusinessException(Messages.RENTS_PAYMENT_PAYED);
	}

	private void checkPaymentExist(int paymentId) {
		
		if (this.paymentDao.getById(paymentId).equals(null)) {
			
			throw new BusinessException(Messages.PAYMENT_NOT_EXİST);
			
		}
		
	}

}

package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.payment.GetPaymentDto;
import com.turkcell.rentacar.business.dtos.payment.ListPaymentDto;
import com.turkcell.rentacar.business.requests.payment.CreateExtraPaymentRequest;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.business.requests.payment.DeletePaymentRequest;
import com.turkcell.rentacar.business.requests.payment.UpdatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface PaymentService {
	
	Result add(CreatePaymentRequest createPaymentRequest) ;
	
	DataResult<List<ListPaymentDto>> getAll();
    DataResult<GetPaymentDto> getById(int paymentId);
    
    Result update(UpdatePaymentRequest updatePaymentRequest) ;

    Result delete(DeletePaymentRequest deletePaymentRequest) ;

    
    Result extraDaysRentCarPayment(CreateExtraPaymentRequest createExtraPaymentRequest);

    void checkIfPaymentIdExist(int paymentId);
	
}

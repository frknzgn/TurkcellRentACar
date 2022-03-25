package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.dtos.payment.GetPaymentDto;
import com.turkcell.rentacar.business.dtos.payment.ListPaymentDto;
import com.turkcell.rentacar.business.requests.payment.CreatePaymentRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/paymentcontroller")
public class PaymentController {
	
	private PaymentService paymentService;
	
	@Autowired
	public PaymentController(PaymentService paymentService) {
		
		this.paymentService = paymentService;
		
	}
	
	@PostMapping("/add")
	public Result add(CreatePaymentRequest createPaymentRequest) {
		
		return this.paymentService.add(createPaymentRequest);
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListPaymentDto>> getAll(){
		
		return this.paymentService.getAll();
		
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetPaymentDto> getById(int paymentId){
		
		return this.paymentService.getById(paymentId);
		
	}
	

}

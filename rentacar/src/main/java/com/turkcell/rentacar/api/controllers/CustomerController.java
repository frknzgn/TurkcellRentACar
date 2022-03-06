package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.customer.GetCustomerDto;
import com.turkcell.rentacar.business.dtos.customer.ListCustomerDto;
import com.turkcell.rentacar.business.requests.customer.CreateCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.DeleteCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.UpdateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		
		this.customerService=customerService;
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCustomerDto>> getall(){
		return this.customerService.getall();
		
	}
	
	@PostMapping("/add")
	public Result add(CreateCustomerRequest createCustomerRequest) throws BusinessException{
		return this.customerService.add(createCustomerRequest);
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetCustomerDto> getById(int id){

		return this.customerService.getById(id);
	}
	
	@PutMapping("/update")	
	public Result update(UpdateCustomerRequest updateCustomerRequest) {
		return this.customerService.update(updateCustomerRequest);
	}
	
	
	@DeleteMapping("/delete")	
	public Result delete(DeleteCustomerRequest deleteCustomerRequest) throws BusinessException{
		return this.customerService.delete(deleteCustomerRequest);
	}

}

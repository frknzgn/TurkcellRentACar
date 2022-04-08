package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.dtos.individualCustomer.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.turkcell.rentacar.business.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/individualcustomers")
public class IndividualCustomerController {
	
	private IndividualCustomerService individualCustomerService;
	
	@Autowired
	public IndividualCustomerController(IndividualCustomerService individualCustomerService) {

		this.individualCustomerService= individualCustomerService;
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListIndividualCustomerDto>> getall(){
		
		return this.individualCustomerService.getall();
		
		
	}
	
	@PostMapping("/add")
	public Result add( CreateIndividualCustomerRequest createIndividualCustomerRequest){
		
		return this.individualCustomerService.add(createIndividualCustomerRequest);
		
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetIndividualCustomerDto> getById(int id){

		return this.individualCustomerService.getById(id);
		
	}
	
	@PutMapping("/update")	
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
		
	}
	
	
	@DeleteMapping("/delete")	
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException{
		
		return this.individualCustomerService.delete(deleteIndividualCustomerRequest);
		
	}

}


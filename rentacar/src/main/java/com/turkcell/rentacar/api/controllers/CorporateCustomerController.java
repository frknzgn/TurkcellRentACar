package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.dtos.corporateCustomer.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.turkcell.rentacar.business.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.corporateCustomer.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/corporatecustomers")
public class CorporateCustomerController {
	
	private CorporateCustomerService corporateCustomerService;
	
	@Autowired
	public CorporateCustomerController(CorporateCustomerService corporateCustomerService) {

		this.corporateCustomerService= corporateCustomerService;
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCorporateCustomerDto>> getall(){
		
		return this.corporateCustomerService.getall();
		
		
	}
	
	@PostMapping("/add")
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException{
		
		return this.corporateCustomerService.add(createCorporateCustomerRequest);
		
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetCorporateCustomerDto> getById(int id){

		return this.corporateCustomerService.getById(id);
		
	}
	
	@PutMapping("/update")	
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		
		return this.corporateCustomerService.update(updateCorporateCustomerRequest);
		
	}
	
	
	@DeleteMapping("/delete")	
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException{
		
		return this.corporateCustomerService.delete(deleteCorporateCustomerRequest);
		
	}

}
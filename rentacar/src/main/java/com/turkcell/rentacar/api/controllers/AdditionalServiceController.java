package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.additionalService.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.addtionalService.CreateAdditonalServiceRequest;
import com.turkcell.rentacar.business.requests.addtionalService.DeleteAdditonalServiceRequest;
import com.turkcell.rentacar.business.requests.addtionalService.UpdateAdditonalServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalservices")
public class AdditionalServiceController {
	
	private AdditionalServiceService additionalServiceService;
	
	public AdditionalServiceController(AdditionalServiceService additionalServiceService) {
		
		this.additionalServiceService = additionalServiceService;
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListAdditionalServiceDto>> getall() throws BusinessException{
		return this.additionalServiceService.getall();
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetAdditionalServiceDto> getById(int id) throws BusinessException{
		return this.additionalServiceService.getById(id);
	}
	
	@PostMapping("/add")
	public Result add(CreateAdditonalServiceRequest createAdditonalServiceRequest) {
		return this.additionalServiceService.add(createAdditonalServiceRequest);
	}
	
	@PutMapping("/update")
	public Result update(UpdateAdditonalServiceRequest updateAdditonalServiceRequest) throws BusinessException {
		return this.additionalServiceService.update(updateAdditonalServiceRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteAdditonalServiceRequest deleteAdditonalServiceRequest) throws BusinessException {
		return this.additionalServiceService.delete(deleteAdditonalServiceRequest);
	}

}

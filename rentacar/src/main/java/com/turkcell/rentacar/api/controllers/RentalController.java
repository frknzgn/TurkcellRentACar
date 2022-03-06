package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.rental.GetRentalDto;
import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;
import com.turkcell.rentacar.business.requests.rental.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.DeleteRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	
	private RentalService rentalService;
	
	@Autowired
	public RentalController(RentalService rentalService) {
		this.rentalService=rentalService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListRentalDto>> getall(){
	
		return	this.rentalService.getall();
	 
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateRentalRequest createRentalRequest) throws BusinessException {
		
		return this.rentalService.add(createRentalRequest);
		
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetRentalDto> getById(int rentalId){
		
		return this.rentalService.getById(rentalId);
	
	}
	
	@PutMapping("/update")
	public Result update(UpdateRentalRequest updateRentalRequest) {
		
		return this.rentalService.update(updateRentalRequest);
		
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		
		return this.rentalService.delete(deleteRentalRequest);
	}
	
	@GetMapping("/getbycarid")
	public DataResult<List<ListRentalDto>> getByCar_carId(int carId){
		
		return this.rentalService.getByCar_carId(carId);
	}
}

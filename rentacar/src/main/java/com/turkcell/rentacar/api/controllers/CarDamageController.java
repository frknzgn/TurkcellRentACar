package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CarDamageService;
import com.turkcell.rentacar.business.dtos.cardamage.GetCarDamageDto;
import com.turkcell.rentacar.business.dtos.cardamage.ListCarDamageDto;
import com.turkcell.rentacar.business.requests.cardamage.CreateCarDamageRequest;
import com.turkcell.rentacar.business.requests.cardamage.DeleteCarDamageRequest;
import com.turkcell.rentacar.business.requests.cardamage.UpdateCarDamageRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/car_damages")
public class CarDamageController {
	
	private CarDamageService carDamageService;
	
	@Autowired
	public CarDamageController(CarDamageService carDamageService) {
		
		this.carDamageService = carDamageService;
	
	}
	
	@PostMapping("/add")
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		
		return this.carDamageService.add(createCarDamageRequest);
		
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCarDamageDto>> getAll(){
		
		return this.carDamageService.getAll();
		
	}
	
	@GetMapping("/getbycardamageıd")
	public DataResult<GetCarDamageDto> getByCarDamageId(int carDamageId){
		
		return this.carDamageService.getByCarDamageId(carDamageId);
		
	}
	
	@PutMapping("/update")
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		
		return this.carDamageService.update(updateCarDamageRequest);
		
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteCarDamageRequest deletCarDamageRequest) {
		
		return this.carDamageService.delete(deletCarDamageRequest);
		
	}

}

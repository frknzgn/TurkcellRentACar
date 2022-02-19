package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.ListCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;

@RestController
@RequestMapping("/api/cars")
public class CarController {
	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService=carService;
	}
	
	@GetMapping("/getall")
	public List<ListCarDto> getall(){
		return this.carService.getall();
	}
	
	@GetMapping("/getbyid")
	public GetCarDto getbyid(@RequestParam int id){
		
		return this.carService.getByCarId(id);
		
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateCarRequest createCarRequest) throws BusinessException {
		this.carService.add(createCarRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		this.carService.delete(deleteCarRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException {
		this.carService.update(updateCarRequest);
	}
	
}

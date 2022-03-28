package com.turkcell.rentacar.api.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.dtos.city.ListCityDto;
import com.turkcell.rentacar.business.requests.city.CreateCityRequest;
import com.turkcell.rentacar.business.requests.city.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.city.UpdateCityRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/cities")
public class CityController {
	
	private CityService cityService;
	
	@Autowired
	public CityController(CityService cityService){
		
		this.cityService = cityService;
		
	}
	
	@PostMapping("/add")
	public Result add(CreateCityRequest createCityRequest){
		
		return this.cityService.add(createCityRequest);
	
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCityDto>> getall(){
		
		return this.cityService.getall();
	
	}
	
	@PutMapping("/update")
	public Result update(UpdateCityRequest updateCityRequest) {
		
		return this.cityService.update(updateCityRequest);
		
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteCityRequest deleteCityRequest) {
		
		return this.cityService.delete(deleteCityRequest);
		
	}

}

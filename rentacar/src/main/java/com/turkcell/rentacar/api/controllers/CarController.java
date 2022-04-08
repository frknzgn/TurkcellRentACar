package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.car.GetCarDto;
import com.turkcell.rentacar.business.dtos.car.ListCarDto;
import com.turkcell.rentacar.business.requests.car.CreateCarRequest;
import com.turkcell.rentacar.business.requests.car.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.car.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/cars")
public class CarController {
	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService=carService;
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) throws BusinessException {

		return this.carService.add(createCarRequest);		
		
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<ListCarDto>> getall(){
		
			return this.carService.getAll();
		
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetCarDto> getbyid(@RequestParam int id){

			return this.carService.getByCarId(id);
	
	}
	
	@GetMapping("/getAllByPage")
	public DataResult<List<ListCarDto>> getAll(int pageNumber, int pageSize) throws BusinessException{
	
			return this.carService.getAllPaged(pageNumber, pageSize);
	
	}
		
	@GetMapping("/getAllSortedByChoice")
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) throws BusinessException{

			return this.carService.getAllSorted(direction);		
			
	}
	
	@GetMapping("/getCarByDailyPrice")
	public DataResult<List<ListCarDto>> getCarByDailyPriceLessThanEqual(double dailyPrice) throws BusinessException{
		
			return this.carService.getCarByDailyPriceLessThanEqual(dailyPrice);
	
	}
	
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) throws BusinessException {

			return this.carService.delete(deleteCarRequest);
		
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException {
	
			return this.carService.update(updateCarRequest);
	
	}
		
}
	


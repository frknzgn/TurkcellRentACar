package com.turkcell.rentacar.api.controllers;

import java.util.List;

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
import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.ListCarByDailyPriceDto;
import com.turkcell.rentacar.business.dtos.ListCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/cars")
public class CarController {
	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService=carService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCarDto>> getall(){
		try {
			return this.carService.getAll();
		} catch (BusinessException e) {
			return new ErrorDataResult<List<ListCarDto>>(e.getMessage());
		}
		
	}
	
	
	@GetMapping("/getbyid")
	public DataResult<GetCarDto> getbyid(@RequestParam int id){
		try {
			return this.carService.getByCarId(id);
		} catch (BusinessException e) {
			return new ErrorDataResult<GetCarDto>(e.getMessage());
		}
		
		
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateCarRequest createCarRequest) throws BusinessException {

		try {
		return this.carService.add(createCarRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}		
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) throws BusinessException {
		try {
			return this.carService.delete(deleteCarRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}
		
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateCarRequest updateCarRequest) throws BusinessException {
		try {
			return this.carService.update(updateCarRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}
	}
		
	@GetMapping("/getAllByPage")
	public DataResult<List<ListCarDto>> getAll(int pageNumber, int pageSize) throws BusinessException{
		try {
			return this.carService.getAllPaged(pageNumber, pageSize);
		} catch (BusinessException e) {
			return new ErrorDataResult<List<ListCarDto>>(e.getMessage());
		}
	}
		
	@GetMapping("/getAllSortedByChoice")
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) throws BusinessException{
		try {
			return this.carService.getAllSorted(direction);
		} catch (BusinessException e) {
			return new ErrorDataResult<List<ListCarDto>>(e.getMessage());
		}		
	}
	
	@GetMapping("/getCarByDailyPrice")
	public DataResult<List<ListCarByDailyPriceDto>> getCarByDailyPriceLessThanEqual(double dailyPrice) throws BusinessException{
		try {
			return this.carService.getCarByDailyPriceLessThanEqual(dailyPrice);
		} catch (BusinessException e) {
			return new ErrorDataResult<List<ListCarByDailyPriceDto>>(e.getMessage());
		}
	}
		
}
	


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

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/colors")
public class ColorController {
	
	private ColorService colorService;
	
	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListColorDto>> getall(){
		
		try {
			return this.colorService.getall();
		} catch (BusinessException e) {
			return new ErrorDataResult<List<ListColorDto>>(e.getMessage());
		}
	
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) throws BusinessException {
		
		try {
			return this.colorService.add(createColorRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}
		
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetColorDto> getbyid(@RequestParam int id) throws BusinessException{
		try {
			return this.colorService.getById(id);
		} catch (BusinessException e) {
			return new ErrorDataResult<GetColorDto>(e.getMessage());
		}
		
		
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteColorRequest deleteColorRequest) throws BusinessException{
		
		try {
			return this.colorService.delete(deleteColorRequest);
		} catch (BusinessException e) {
			return new ErrorResult(null);
		}
		
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateColorRequest updateColorRequest) throws BusinessException {
		
		try {
			return this.colorService.update(updateColorRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}
		
	}
	
	
	
}

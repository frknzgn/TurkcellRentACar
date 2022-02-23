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

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListBrandDto>> getall(){
		try {
			return this.brandService.getall();
		} catch (BusinessException e) {
			return new ErrorDataResult<List<ListBrandDto>>(e.getMessage());
		}
		
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetBrandDto> getbyid(@RequestParam int id){
		try {
			return this.brandService.getById(id);
		} catch (BusinessException e) {
			return new ErrorDataResult<GetBrandDto>(e.getMessage());
		}
		
		
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) throws BusinessException {
		try {
			return this.brandService.add(createBrandRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}
		
	}
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		try {
			return this.brandService.delete(deleteBrandRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}
		
	}
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		try {
			return this.brandService.update(updateBrandRequest);
		} catch (BusinessException e) {
			return new ErrorResult(e.getMessage());
		}
		
	}
	
	

}

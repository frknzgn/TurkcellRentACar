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
import com.turkcell.rentacar.business.dtos.brand.GetBrandDto;
import com.turkcell.rentacar.business.dtos.brand.ListBrandDto;
import com.turkcell.rentacar.business.requests.brand.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brand.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.brand.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/brands")
public class BrandController {
	
	private BrandService brandService;
	
	@Autowired
	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateBrandRequest createBrandRequest) {

			return this.brandService.add(createBrandRequest);
				
	}
	
	
	@GetMapping("/getall")
	public DataResult<List<ListBrandDto>> getall(){
	
			return this.brandService.getall();	
			
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetBrandDto> getbyid(@RequestParam int id){
			
		return this.brandService.getById(id);
	
	}
	
	
	@PutMapping("/update")
	public Result update(@RequestBody UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		try {
			return this.brandService.update(updateBrandRequest);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		}
	}
	
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
		
			return this.brandService.delete(deleteBrandRequest);

	}

}

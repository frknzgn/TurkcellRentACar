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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.color.GetColorDto;
import com.turkcell.rentacar.business.dtos.color.ListColorDto;
import com.turkcell.rentacar.business.requests.color.CreateColorRequest;
import com.turkcell.rentacar.business.requests.color.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.color.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


@RestController
@RequestMapping("/api/colors")
public class ColorController {
	
	private ColorService colorService;
	
	@Autowired
	public ColorController(ColorService colorService) {
		
		this.colorService = colorService;
		
	}
	
	
	@PostMapping("/add")
	public Result add(@RequestBody CreateColorRequest createColorRequest) throws BusinessException {
		
		return this.colorService.add(createColorRequest);

	}
	
	
	@GetMapping("/getall")
	public DataResult<List<ListColorDto>> getall(){
		
		return this.colorService.getall();
	
	}
	
	@GetMapping("/getbyid")
	public DataResult<GetColorDto> getbyid(@RequestParam @Valid int colorId) {
		
			return this.colorService.getById(colorId);

	}
	
	
	@PutMapping("/update")
	public Result update(@RequestBody @Valid UpdateColorRequest updateColorRequest) throws BusinessException {
		
		return this.colorService.update(updateColorRequest);

	}
	
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody @Valid DeleteColorRequest deleteColorRequest) throws BusinessException{
		
		return this.colorService.delete(deleteColorRequest);
		
	}
	
}

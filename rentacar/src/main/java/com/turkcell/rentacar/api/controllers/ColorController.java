package com.turkcell.rentacar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;


@RestController
@RequestMapping("/api/colors")
public class ColorController {
	
	private ColorService colorService;
	
	@Autowired
	public ColorController(ColorService colorService) {
		this.colorService = colorService;
	}
	
	@GetMapping("/getall")
	public List<ListColorDto> getall(){
		return this.colorService.getall();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CreateColorRequest createColorRequest) throws BusinessException {
		this.colorService.add(createColorRequest);
	}
	
	
	
}

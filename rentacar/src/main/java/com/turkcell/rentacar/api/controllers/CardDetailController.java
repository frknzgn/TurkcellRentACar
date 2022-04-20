package com.turkcell.rentacar.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CardDetailService;
import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailRequest;
import com.turkcell.rentacar.business.requests.cardDetail.DeleteCardDetailRequest;
import com.turkcell.rentacar.business.requests.cardDetail.UpdateCardDetailRequest;
import com.turkcell.rentacar.core.utilities.results.Result;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/card_details")
public class CardDetailController {
	
	CardDetailService cardDetailService;
	
	@Autowired
	public CardDetailController(CardDetailService carDetailService) {
		
		this.cardDetailService = carDetailService;
		
	}
	
	@PostMapping("/add")
	public Result add(CreateCardDetailRequest createCardDetailRequest) {
		
		return this.cardDetailService.add(createCardDetailRequest);
		
	}
	
	@GetMapping("/getall")
	public Result getAll() {
		
		return this.cardDetailService.getall();
		
	}
	
	
	@PutMapping("/update")
	public Result update(UpdateCardDetailRequest updateCardDetailRequest) {
		
		return this.cardDetailService.update(updateCardDetailRequest);
		
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteCardDetailRequest deleteCardDetailRequest) {
		
		return this.cardDetailService.delete(deleteCardDetailRequest);
		
	}
}

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

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.rental.GetRentalDto;
import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;
import com.turkcell.rentacar.business.requests.rental.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.DeleteRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalReturnRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

	 	private RentalService rentalService;

	    @Autowired
	    public RentalController(RentalService rentalService) {
	        this.rentalService = rentalService;
	    }


	    @PutMapping("/updateForReturnCar")
	    Result updateForReturnCar(@RequestBody @Valid UpdateRentalReturnRequest updateRentalReturnRequest) throws BusinessException {
	    	
	        return this.rentalService.updateForReturnCar(updateRentalReturnRequest);
	        
	    }
	    

	    @PostMapping("/addForIndividual")
	    Result addForIndividual(@RequestBody @Valid CreateRentalRequest createRentalRequest) throws BusinessException {
	    	
	        return this.rentalService.addForIndividual(createRentalRequest);
	        
	    }

	    @PostMapping("/addForCorporate")
	    Result addForCorporate(@RequestBody @Valid CreateRentalRequest createRentalForCorporateRequest) throws BusinessException {
	    	
	        return this.rentalService.addForCorporate(createRentalForCorporateRequest);
	        
	    }

	    @PutMapping("/update")
	    Result update(@RequestBody @Valid UpdateRentalRequest updateRentalRequest) throws BusinessException {
	    	
	        return this.rentalService.update(updateRentalRequest);
	        
	    }

	    @DeleteMapping("/delete")
	    Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) throws BusinessException {
	    	
	        return this.rentalService.delete(deleteRentalRequest);
	        
	    }

	    @GetMapping("/getAll")
	    DataResult<List<ListRentalDto>> getAll() {
	    	
	        return this.rentalService.getAll();
	        
	    }

	    @GetMapping("/getRentCarsByCarId")
	    DataResult<List<ListRentalDto>> getRentCarsByCarId(@RequestParam int carId) throws BusinessException {
	    	
	        return this.rentalService.getRentCarsByCarId(carId);
	        
	    }

	    @GetMapping("/getById")
	    DataResult<GetRentalDto> getById(@RequestParam int rentCarId) throws BusinessException {
	    	
	        return this.rentalService.getById(rentCarId);
	        
	    }
}

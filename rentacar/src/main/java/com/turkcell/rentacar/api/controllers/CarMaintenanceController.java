package com.turkcell.rentacar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.dtos.carMaintenance.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

@ComponentScan({"com.turkcell.rentacar.business.abstracts"})
@RestController
@RequestMapping("/api/carmaintenances")
public class CarMaintenanceController {
	private CarMaintenanceService carMaintenanceService;
	
	@Autowired
	public CarMaintenanceController(CarMaintenanceService carMaintenanceService){
		this.carMaintenanceService=carMaintenanceService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<ListCarMaintenanceDto>> getAll(){
		
			return	this.carMaintenanceService.getAll();
		
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		return this.carMaintenanceService.add(createCarMaintenanceRequest);
	}
	
	@GetMapping("/getbycarid")
	public DataResult<List<ListCarMaintenanceDto>> getByCarId(int carId){
		return this.carMaintenanceService.getByCarId(carId);
	}
	
	@GetMapping("/getbycarmaintenanceid")
	public DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId){
		return this.carMaintenanceService.getByCarMaintenanceId(carMaintenanceId);
	}
	
	@PutMapping("/update")
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		return this.carMaintenanceService.update(updateCarMaintenanceRequest);
	}
	
	@DeleteMapping("/delete")
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		return this.carMaintenanceService.delete(deleteCarMaintenanceRequest);
	}
	
}

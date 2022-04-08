package com.turkcell.rentacar.business.requests.carMaintenance;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarMaintenanceRequest {
	
	@NotNull
	@Min(0)	
	private int carMaintenanceId;
	
	@NotNull
	@Min(0)	
	private int carId;
	
	
	@NotNull
	@Size(min=2,max=250)
	private String description;
	
	
	private LocalDate maintenanceDropDate;
	private LocalDate maintenanceReturnDate;
	
}

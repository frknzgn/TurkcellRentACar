package com.turkcell.rentacar.business.dtos.carMaintenance;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCarMaintenanceDto {
	
	private int carCarId;
	private int carMaintenanceId;
	private String maintenanceDescription;
	private LocalDate maintenanceDropDate;
	private LocalDate maintenanceReturnDate;
	
}

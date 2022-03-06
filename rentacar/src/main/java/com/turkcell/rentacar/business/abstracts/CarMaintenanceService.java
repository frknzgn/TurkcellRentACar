package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.carMaintenance.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


public interface CarMaintenanceService {
	
	DataResult<List<ListCarMaintenanceDto>> getAll();
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest);
	DataResult<List<ListCarMaintenanceDto>> getByCarId(int carId);
	DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId);
	Result update(UpdateCarMaintenanceRequest carMaintenanceRequest);
	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
}

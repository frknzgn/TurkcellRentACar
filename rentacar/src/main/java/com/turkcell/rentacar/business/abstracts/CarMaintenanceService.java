package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.carMaintenance.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


public interface CarMaintenanceService {
	
	Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException;
	
	DataResult<List<ListCarMaintenanceDto>> getAll();
	DataResult<List<ListCarMaintenanceDto>> getByCarId(int carId);
	DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId);
	
	Result update(UpdateCarMaintenanceRequest carMaintenanceRequest);
	
	Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest);
	
	void checkIfCarMaintenanceReturnDateByCarId(int carId) throws BusinessException;
	
}

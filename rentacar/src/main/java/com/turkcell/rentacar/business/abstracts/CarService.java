package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.ListCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CarService {
	
	DataResult<List<ListCarDto>> getall() throws BusinessException;
	Result add(CreateCarRequest createCarRequest) throws BusinessException;
	DataResult<GetCarDto> getByCarId(int carId) throws BusinessException;
	Result update(UpdateCarRequest carRequest) throws BusinessException;
	Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException;
	
	
}

package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.ListCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;

public interface CarService {
	
	List<ListCarDto> getall();
	void add(CreateCarRequest createCarRequest) throws BusinessException;
	GetCarDto getByCarId(int carId);
	void update(UpdateCarRequest carRequest);
	void delete(DeleteCarRequest deleteCarRequest);
	
	
}

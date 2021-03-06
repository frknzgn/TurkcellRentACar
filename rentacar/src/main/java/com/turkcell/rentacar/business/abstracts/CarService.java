package com.turkcell.rentacar.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Sort;

import com.turkcell.rentacar.business.dtos.car.GetCarDto;
import com.turkcell.rentacar.business.dtos.car.ListCarDto;
import com.turkcell.rentacar.business.requests.car.CreateCarRequest;
import com.turkcell.rentacar.business.requests.car.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.car.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


public interface CarService {
	
	Result add(CreateCarRequest createCarRequest) throws BusinessException;
	
	
	DataResult<List<ListCarDto>> getAll() throws BusinessException;
	DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) throws BusinessException;
	DataResult<List<ListCarDto>> getAllPaged(int pageNumber, int pageSize) throws BusinessException;
	DataResult<List<ListCarDto>> getCarByDailyPriceLessThanEqual(double dailyPrice) throws BusinessException;
	DataResult<GetCarDto> getByCarId(int carId) throws BusinessException;
	
	
	Result update(UpdateCarRequest updateCarRequest) throws BusinessException;
	
	
	Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException;
	
	
	void checkCarExist(int carId);
	void isCarReturnFromRent(int carId, double returnDistance) throws BusinessException;
	double totalCarDailyPriceCalculator(int carId, LocalDate dateOfIssue, LocalDate dateOfReceipt);
	
}

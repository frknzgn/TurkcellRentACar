package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.rental.GetRentalDto;
import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;
import com.turkcell.rentacar.business.requests.rental.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.DeleteRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalReturnRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entites.concretes.Rental;


public interface RentalService {
	
	DataResult<Integer> addForIndividual(CreateRentalRequest createRentalRequest) throws BusinessException;
    Result addForCorporate(CreateRentalRequest createRentalRequest) throws BusinessException;
	
	DataResult<List<ListRentalDto>> getAll();
	DataResult<GetRentalDto> getById(int rentalId) ;
	DataResult<List<ListRentalDto>> getRentCarsByCarId(int carId) throws BusinessException;
	
	Result update(UpdateRentalRequest updateRentalRequest);
	Result updateForReturnCar(UpdateRentalReturnRequest updateRentalReturnRequest) throws BusinessException;
	
	Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException;
	
	void checkIfRentalExists(int rentCarId) throws BusinessException;
	void checkIfRentalReturnDateByCarId(int carId) throws BusinessException;
	double totalRentalDailyPriceAndDifferntCityCalculator(Rental rental) throws BusinessException;
	
}

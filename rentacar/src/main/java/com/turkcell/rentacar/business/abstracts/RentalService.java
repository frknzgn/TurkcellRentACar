package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.rental.GetRentalDto;
import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;
import com.turkcell.rentacar.business.requests.rental.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.DeleteRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


public interface RentalService {
	
	Result add(CreateRentalRequest createRentalRequest) throws BusinessException;
	
	DataResult<List<ListRentalDto>> getall();
	DataResult<GetRentalDto> getById(int rentalId) ;
	DataResult<List<ListRentalDto>> getByCar_carId(int carId);
	
	Result update(UpdateRentalRequest updateRentalRequest);
	
	Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException;
	
	
}

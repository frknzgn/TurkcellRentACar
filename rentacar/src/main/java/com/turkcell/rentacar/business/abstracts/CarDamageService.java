package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.cardamage.GetCarDamageDto;
import com.turkcell.rentacar.business.dtos.cardamage.ListCarDamageDto;
import com.turkcell.rentacar.business.requests.cardamage.CreateCarDamageRequest;
import com.turkcell.rentacar.business.requests.cardamage.DeleteCarDamageRequest;
import com.turkcell.rentacar.business.requests.cardamage.UpdateCarDamageRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CarDamageService {
	
	Result add(CreateCarDamageRequest createCarDamageRequest);
	
	DataResult<List<ListCarDamageDto>> getAll();
	DataResult<GetCarDamageDto> getByCarDamageId(int carDamageId);
	
	Result update(UpdateCarDamageRequest updateCarDamageRequest);
	
	Result delete(DeleteCarDamageRequest deletCarDamageRequest);	
	
}

package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.city.ListCityDto;
import com.turkcell.rentacar.business.requests.city.CreateCityRequest;
import com.turkcell.rentacar.business.requests.city.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.city.UpdateCityRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CityService {
	
	Result add(CreateCityRequest createCityRequest);
	
	DataResult<List<ListCityDto>> getall();
	
	Result update(UpdateCityRequest updateCityRequest);
	
	Result delete(DeleteCityRequest deleteCityRequest);

	void checkIfCityIdExists(int CityId);
	
}

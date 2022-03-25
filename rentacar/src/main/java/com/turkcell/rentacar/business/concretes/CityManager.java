package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.dtos.city.ListCityDto;
import com.turkcell.rentacar.business.requests.city.CreateCityRequest;
import com.turkcell.rentacar.business.requests.city.DeleteCityRequest;
import com.turkcell.rentacar.business.requests.city.UpdateCityRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CityDao;
import com.turkcell.rentacar.entites.concretes.City;

@Service
public class CityManager implements CityService{
	
	private CityDao cityDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CityManager(CityDao cityDao, ModelMapperService modelMapperService) {
		
		this.cityDao = cityDao;
		this.modelMapperService = modelMapperService;
		
	}
	
	
	@Override
	public Result add(CreateCityRequest createCityRequest) {
		
		checkCityNameExist(createCityRequest.getCityName());
		
		City city = this.modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityDao.save(city);
		
		return new SuccessResult(Messages.CİTYADDED);
		
	}


	@Override
	public DataResult<List<ListCityDto>> getall() {
		
		var result = this.cityDao.findAll();
		List<ListCityDto> response = result.stream().
							map(city-> this.modelMapperService.forDto().
									map(city, ListCityDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(response, "City.Listed.");
		
	}
	

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		
		checkIfCityExistById(updateCityRequest.getCityId());
		
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityDao.save(city);
		
		return new SuccessResult("City.Updated.");
		
	}
	

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		
		checkIfCityExistById(deleteCityRequest.getCityId());
		
		City city = this.modelMapperService.forRequest().map(deleteCityRequest, City.class);
		this.cityDao.delete(city);
		
		return new SuccessResult("City.Deleted");
		
	}
	
	
	private void checkCityNameExist(String cityName) {
		
		List<City> cities = this.cityDao.findAll();
		
		for (City city : cities ) {
			if(city.getCityName().toLowerCase().matches(cityName.toLowerCase())) {
				
				throw new BusinessException(Messages.CİTYEXİST);
				
			}
		}
		
	}
	
	private void checkIfCityExistById(int cityId) {
		
		if(this.cityDao.getByCityId(cityId)==null) {
			
			throw new BusinessException(Messages.CİTYNOTEXİST);
			
		}
	}

}

	

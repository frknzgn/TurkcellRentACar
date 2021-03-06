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
		
		return new SuccessResult(Messages.CİTY_ADDED);
		
	}


	@Override
	public DataResult<List<ListCityDto>> getall() {
		
		var result = this.cityDao.findAll();
		List<ListCityDto> response = result.stream().
							map(city-> this.modelMapperService.forDto().
									map(city, ListCityDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCityDto>>(response, Messages.CITY_LISTED);
		
	}
	

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		
		checkIfCityIdExists(updateCityRequest.getCityId());
		
		City city = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityDao.save(city);
		
		return new SuccessResult(Messages.CITY_UPDATED);
		
	}
	

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		
		checkIfCityIdExists(deleteCityRequest.getCityId());
		
		City city = this.modelMapperService.forRequest().map(deleteCityRequest, City.class);
		this.cityDao.delete(city);
		
		return new SuccessResult(Messages.CİTY_DELETED);
		
	}
	
	
	private void checkCityNameExist(String cityName) {
		
		List<City> cities = this.cityDao.findAll();
		System.out.println(cities);
		for(City city : cities) {
			
			if(city.getCityName().toLowerCase().equals(cityName.toLowerCase())) {
				
				throw new BusinessException(Messages.CITY_EXİST);
				
			}
		}			
	}


	@Override
	public void checkIfCityIdExists(int cityId) {
		
		if (!this.cityDao.existsById(cityId)) {
			
            throw new BusinessException(Messages.CİTY_NOT_EXİST);
            
        }
		
	}

}

	

package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarDamageService;
import com.turkcell.rentacar.business.dtos.cardamage.GetCarDamageDto;
import com.turkcell.rentacar.business.dtos.cardamage.ListCarDamageDto;
import com.turkcell.rentacar.business.requests.cardamage.CreateCarDamageRequest;
import com.turkcell.rentacar.business.requests.cardamage.DeleteCarDamageRequest;
import com.turkcell.rentacar.business.requests.cardamage.UpdateCarDamageRequest;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarDamageDao;
import com.turkcell.rentacar.entites.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService {
	
	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarDamageManager(CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
		
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
		
	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult("CarDamage.Added");
		
	}

	
	@Override
	public SuccessDataResult<List<ListCarDamageDto>> getAll() {
		
		var result = this.carDamageDao.findAll();
		
		List<ListCarDamageDto> response = result.stream().
				map(damage->this.modelMapperService.forDto().
						map(damage, ListCarDamageDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDamageDto>>(response, "CarDamage.Listed");
	}

	@Override
	public DataResult<GetCarDamageDto> getByCarDamageId(int carDamageId) {
		
		CarDamage result = this.carDamageDao.getById(carDamageId);
		GetCarDamageDto response = this.modelMapperService.forDto().map(result, GetCarDamageDto.class);
		
		return new SuccessDataResult<GetCarDamageDto>(response, "CarDamage.GetbyId");
		
	}

	
	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult("CarDamage.Updated");
		
	}

	@Override
	public Result delete(DeleteCarDamageRequest deletCarDamageRequest) {
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(deletCarDamageRequest, CarDamage.class);
		this.carDamageDao.delete(carDamage);
		
		return new SuccessResult("CarDamage.Deleted");
				
	}
	
}


	
	
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
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarDamageDao;
import com.turkcell.rentacar.dataAccess.abstracts.CarDao;
import com.turkcell.rentacar.entites.concretes.CarDamage;

@Service
public class CarDamageManager implements CarDamageService {
	
	private CarDamageDao carDamageDao;
	private ModelMapperService modelMapperService;
	private CarDao carDao;
	
	@Autowired
	public CarDamageManager(CarDao carDao, CarDamageDao carDamageDao, ModelMapperService modelMapperService) {
		
		this.carDamageDao = carDamageDao;
		this.modelMapperService = modelMapperService;
		this.carDao = carDao;
		
	}

	@Override
	public Result add(CreateCarDamageRequest createCarDamageRequest) {
		
		checkCarExist(createCarDamageRequest.getCarId());
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(createCarDamageRequest, CarDamage.class);		
		
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult(Messages.CAR_DAMAGE_ADDED);
		
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
		
		checkCarDamageNotExist(carDamageId);
		
		CarDamage result = this.carDamageDao.getById(carDamageId);
		GetCarDamageDto response = this.modelMapperService.forDto().map(result, GetCarDamageDto.class);
		
		return new SuccessDataResult<GetCarDamageDto>(response, "CarDamage.GetbyId");
		
	}
	

	@Override
	public Result update(UpdateCarDamageRequest updateCarDamageRequest) {
		
		checkCarDamageNotExist(updateCarDamageRequest.getCarDamageId());
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(updateCarDamageRequest, CarDamage.class);
		this.carDamageDao.save(carDamage);
		
		return new SuccessResult("CarDamage.Updated");
		
	}

	@Override
	public Result delete(DeleteCarDamageRequest deleteCarDamageRequest) {
		
		checkCarDamageNotExist(deleteCarDamageRequest.getCarDamageId());
		
		CarDamage carDamage = this.modelMapperService.forRequest().map(deleteCarDamageRequest, CarDamage.class);
		this.carDamageDao.delete(carDamage);
		
		return new SuccessResult("CarDamage.Deleted");
				
	}
	
	
	private void checkCarExist(int carId) {
		
		if(this.carDao.getById(carId) == null) {
			
			throw new BusinessException(Messages.CARNOTEXİST);
			
		}
	}
	
	private void checkCarDamageNotExist(int carDamageId) {
		
		if(this.carDamageDao.getByCarDamageId(carDamageId) == null) {
			
			throw new BusinessException(Messages.CARDAMAGENOTEXİST);
			
		}
		
	}
	
}


	
	
 package com.turkcell.rentacar.business.concretes;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.car.GetCarDto;
import com.turkcell.rentacar.business.dtos.car.ListCarByDailyPriceDto;
import com.turkcell.rentacar.business.dtos.car.ListCarDto;
import com.turkcell.rentacar.business.requests.car.CreateCarRequest;
import com.turkcell.rentacar.business.requests.car.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.car.UpdateCarRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarDao;
import com.turkcell.rentacar.entites.concretes.Car;



@Service
public class CarManager implements CarService{

	private CarDao carDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarManager(CarDao carDao,ModelMapperService modelMapperService) {
		
		this.carDao=carDao;
		this.modelMapperService=modelMapperService;
		
	}
	

	@Override
	//@Transactional(propagation = Propagation.REQUIRED)
	public Result add(CreateCarRequest createCarRequest) throws BusinessException {
		
		checkIfCarExist(createCarRequest);
		
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		
		this.carDao.save(car);
		
		return new SuccessResult(Messages.CAR_ADDED);
		
	
	}
	
	
	@Override
	public DataResult<List<ListCarDto>> getAll() throws BusinessException {
	
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream().map(car->this.modelMapperService.forDto().map(car, ListCarDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response,Messages.CAR_LİSTED);
		
	}

	
	@Override
	public DataResult<GetCarDto> getByCarId(int carId) throws BusinessException {
		
		checkIfIdNotExist(carId);
		
		Car result = this.carDao.getById(carId);
		GetCarDto response = this.modelMapperService.forDto().map(result, GetCarDto.class);
		
		return new SuccessDataResult<GetCarDto>(response, Messages.CAR_GETBY_ID);
		
	}
	
	@Override
	public DataResult<List<ListCarDto>> getAllPaged(int pageNumber, int pageSize) {
		
		Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
		
		var result = this.carDao.findAll(pageable).getContent();
		List<ListCarDto> response = result.stream().
				map(car->this.modelMapperService.forDto()
									.map(car, ListCarDto.class)).collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LİSTEDBY_PAGED);
		
	}

	@Override
	public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) throws BusinessException {
		
		Sort sort = Sort.by(direction,"dailyPrice");
		
		var result = this.carDao.findAll(sort);
		List<ListCarDto> response = result.stream().
				map(car->this.modelMapperService.forDto().
						map(car, ListCarDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LİSTED);
		
	}

	@Override
	public DataResult<List<ListCarByDailyPriceDto>> getCarByDailyPriceLessThanEqual(double dailyPrice) throws BusinessException {
		
		var result = this.carDao.getCarByDailyPriceLessThanEqual(dailyPrice);
		List<ListCarByDailyPriceDto> response = result.stream().
				map(car->this.modelMapperService.forDto()
									.map(car, ListCarByDailyPriceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarByDailyPriceDto>>(response, Messages.CAR_LİSTEDBY_DAİLYPRİCELESSTHANEQUAL);
		
	}

	
	@Override
	public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
		
		checkIfIdExist(updateCarRequest.getCarId());
		
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		this.carDao.save(car);
		
		return new SuccessResult(Messages.CAR_UPDATED);
		
	}

	
	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		
		checkIfIdExist(deleteCarRequest.getCarId());
		
		Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		this.carDao.delete(car);
		
		return new SuccessResult(Messages.CAR_DELETED);
		
	}
	
	
	public void checkIfIdExist(int carId){
		
		if(this.carDao.getById(carId) != null) {
			
			throw new BusinessException(Messages.CAR_EXİST);
			
		}
	}
	
	public void checkIfIdNotExist(int carId){
		
		if(this.carDao.getById(carId) == null) {
			
			throw new BusinessException(Messages.CAR_NOT_EXİST);
			
		}
	}
	
	private void checkIfCarExist(CreateCarRequest createCarRequest) {
		
		List<Car> cars = this.carDao.findAll();
		
		for (Car car : cars) {
			if(car.getDescription() == createCarRequest.getDescription() &&
					car.getMilage() == createCarRequest.getMilage()) {
				
				throw new BusinessException(Messages.CAR_EXİST);
				
			}
		}
		
	}


	@Override
	public void checkCarExist(int carId) {
		
		List<Car> cars = this.carDao.findAll();
		Car car = this.carDao.getByCarId(carId);
		
		for (Car lookinForCar : cars) {
			if(lookinForCar.equals(car) || car == lookinForCar) {
				
				throw new BusinessException(Messages.CAR_EXİST);
				
			}
		}
		
	}
	


	


}

package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.GetCarDto;
import com.turkcell.rentacar.business.dtos.ListCarDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
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
	public DataResult<List<ListCarDto>> getall() {
		var result = this.carDao.findAll();
		List<ListCarDto> response = result.stream().map(car->this.modelMapperService.forDto()
									.map(car, ListCarDto.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarDto>>(response, "Data Listelendi");
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) throws BusinessException {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult(car.getDescription()+"isimli Araç Eklendi.");
		
	}

	@Override
	public DataResult<GetCarDto> getByCarId(int carId) {
		Car result = this.carDao.getByCarId(carId);
		GetCarDto response = this.modelMapperService.forDto().map(result, GetCarDto.class);
		return new SuccessDataResult<GetCarDto>(response, "Id'si "+carId+" olan araç getirildi.");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
		 this.carDao.save(car);
		 return new SuccessResult(updateCarRequest.getCarId()+" 'li araç veri tabanında güncellendi.");
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		this.carDao.delete(car);
		return new SuccessResult();
		
	}

}

package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.carMaintenance.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarMaintenanceDao;
import com.turkcell.rentacar.entites.concretes.CarMaintenance;

@Service
public class CarMaintenanceManager implements CarMaintenanceService {

	private CarMaintenanceDao carMaintenanceDao;
	private ModelMapperService modelMapperService;
	private RentalService rentalService;
	private CarService carService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao,@Lazy RentalService rentalService, ModelMapperService modelMapperService,@Lazy CarService carService) {
		
		this.carMaintenanceDao=carMaintenanceDao;
		this.rentalService = rentalService;
		this.modelMapperService=modelMapperService;
		this.carService = carService;
		
	}
	
	
	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest){
		
		this.carService.checkCarExist(createCarMaintenanceRequest.getCarId());
		this.rentalService.checkIfRentalReturnDateByCarId(createCarMaintenanceRequest.getCarId());
		checkIfCarMaintenanceReturnDateByCarId(createCarMaintenanceRequest.getCarId());
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		carMaintenance.setCarMaintenanceId(0);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CAR_MAINTENANCE_ADDED);
		
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		
		var result = this.carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response = result.stream().
									map(carMaintenance->this.modelMapperService.forDto().
										map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, Messages.CAR_MAINTENANCE_LISTED);	
	
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getByCarId(int carId) {
		
		CheckIfCarExist(carId);
		
		var result = this.carMaintenanceDao.getAllByCarCarId(carId);
		List<ListCarMaintenanceDto> response = result.stream().
									map(carMaintenance->this.modelMapperService.forDto().
											map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response,Messages.CAR_MAINTENANCE_GETBY_CAR_ID);
		
	}
	
	@Override
	public DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId) {
		
		CheckIfIdExist(carMaintenanceId);
		
		CarMaintenance result = this.carMaintenanceDao.getById(carMaintenanceId);
		GetCarMaintenanceDto response = this.modelMapperService.forDto().map(result, GetCarMaintenanceDto.class);
		
		return new SuccessDataResult<GetCarMaintenanceDto>(response,Messages.CAR_MAINTENANCE_GETBY_ID);
		
	}
	

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		CheckIfIdExist(updateCarMaintenanceRequest.getCarMaintenanceId());
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(Messages.CAR_MAINTENANCE_UPDATED);
	
	}
	

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		
		CheckIfIdExist(deleteCarMaintenanceRequest.getCarMaintenanceId());
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.delete(carMaintenance);
		
		return new SuccessResult(Messages.CAR_MAINTENANCE_DELETED);
	
	}
	
	
	public void checkIfCarMaintenanceReturnDateByCarId(int carId) throws BusinessException {
        if (this.carMaintenanceDao.getCarMaintenanceByCarCarIdAndMaintenanceReturnDateIsNull(carId) != null) {
            throw new BusinessException(Messages.CAR_IS_IN_MAINTENANCE_NOW_BY_CAR_ID);
        }

    }
	
	private void CheckIfIdExist(int carMaintenancesId) {
		
		if(this.carMaintenanceDao.getById(carMaintenancesId).equals(null)) {
			
			throw new BusinessException(Messages.CAR_MAINTENANCE_NOT_EXIST);
			
		}
	}
	
	private void CheckIfCarExist(int carId) {
		
		if(this.rentalService.getRentCarsByCarId(carId).equals(null)) {
			
			throw new BusinessException(Messages.CAR_NOT_EXİST);
			
		}
	}

}

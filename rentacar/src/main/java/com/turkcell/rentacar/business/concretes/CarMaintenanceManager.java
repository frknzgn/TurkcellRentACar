package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.carMaintenance.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;
import com.turkcell.rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
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
	private RentalService rentalService;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao,@Lazy RentalService rentalService, ModelMapperService modelMapperService) {
		
		this.carMaintenanceDao=carMaintenanceDao;
		this.rentalService = rentalService;
		this.modelMapperService=modelMapperService;
		
	}
	
	
	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) throws BusinessException {
		
		CheckIfCarRented(createCarMaintenanceRequest.getCarId());
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(carMaintenance.getDescription()+"isimli Aracın bakım bilgileri Eklendi.");
		
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		
		var result = this.carMaintenanceDao.findAll();
		List<ListCarMaintenanceDto> response = result.stream().
									map(carMaintenance->this.modelMapperService.forDto().
										map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, "Data Listelendi.");	
	
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getByCarId(int carId) {
		
		CheckIfCarExist(carId);
		
		var result = this.carMaintenanceDao.getByCar_Id(carId);
		List<ListCarMaintenanceDto> response = result.stream().
									map(carMaintenance->this.modelMapperService.forDto().
											map(carMaintenance, ListCarMaintenanceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, "CarMaintenance.GetByCarId");
		
	}
	
	@Override
	public DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId) {
		
		CheckIfIdExist(carMaintenanceId);
		
		CarMaintenance result = this.carMaintenanceDao.getByCarMaintenanceId(carMaintenanceId);
		GetCarMaintenanceDto response = this.modelMapperService.forDto().map(result, GetCarMaintenanceDto.class);
		
		return new SuccessDataResult<GetCarMaintenanceDto>(response);
		
	}
	

	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		CheckIfIdExist(updateCarMaintenanceRequest.getId());
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		
		return new SuccessResult(updateCarMaintenanceRequest.getCarId()+" CarMaintenance.Updated");
	
	}
	

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		
		CheckIfIdExist(deleteCarMaintenanceRequest.getId());
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.delete(carMaintenance);
		
		return new SuccessResult(deleteCarMaintenanceRequest.getId()+" 'li araç bakım bilgileri veri tabanından silindi.");
	
	}
	

	private void CheckIfCarRented(int carId) throws BusinessException {
		
		List<ListRentalDto> rentals=this.rentalService.getByCar_carId(carId).getData();
		for (ListRentalDto listRentalDto : rentals) {
			
			if(listRentalDto.getReturnDate()==null) {
				
				throw new BusinessException("Car has been rented.");
				
			}
		}
		
	}
	
	private void CheckIfIdExist(int carMaintenancesId) {
		
		if(this.carMaintenanceDao.getByCarMaintenanceId(carMaintenancesId).equals(null)) {
			
			throw new BusinessException("Id is Null.");
			
		}
	}
	
	private void CheckIfCarExist(int carId) {
		
		if(this.rentalService.getByCar_carId(carId).equals(null)) {
			
			throw new BusinessException("Id is Null.");
			
		}
	}

}

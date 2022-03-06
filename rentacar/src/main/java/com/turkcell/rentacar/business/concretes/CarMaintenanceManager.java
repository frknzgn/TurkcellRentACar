package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.dtos.carMaintenance.GetCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.requests.carMaintenance.CreateCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.DeleteCarMaintenanceRequest;
import com.turkcell.rentacar.business.requests.carMaintenance.UpdateCarMaintenanceRequest;
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
	
	@Autowired
	public CarMaintenanceManager(CarMaintenanceDao carMaintenanceDao, ModelMapperService modelMapperService) {
		this.carMaintenanceDao=carMaintenanceDao;
		this.modelMapperService=modelMapperService;
	}
	
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getAll() {
		
		var result = this.carMaintenanceDao.findAll();
		
		List<ListCarMaintenanceDto> response = result.stream().
				map(carMaintenance->this.modelMapperService.forDto().
						map(carMaintenance, ListCarMaintenanceDto.class)).
				collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, "Data Listelendi.");	
	
	}
	
	@Override
	public DataResult<List<ListCarMaintenanceDto>> getByCarId(int carId) {
		
		var result = this.carMaintenanceDao.getByCar_CarId(carId);
		
		List<ListCarMaintenanceDto> response = result.stream().
				map(carMaintenance->this.modelMapperService.forDto().
						map(carMaintenance, ListCarMaintenanceDto.class)).
				collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCarMaintenanceDto>>(response, "Data Listelendi.");
	}

	@Override
	public Result add(CreateCarMaintenanceRequest createCarMaintenanceRequest) {
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(createCarMaintenanceRequest, CarMaintenance.class);
		this.carMaintenanceDao.save(carMaintenance);
		return new SuccessResult(carMaintenance.getDescription()+"isimli Aracın bakım bilgileri Eklendi.");
		
	}


	@Override
	public Result update(UpdateCarMaintenanceRequest updateCarMaintenanceRequest) {
		
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(updateCarMaintenanceRequest, CarMaintenance.class);
		 this.carMaintenanceDao.save(carMaintenance);
		 return new SuccessResult(updateCarMaintenanceRequest.getCarId()+" 'li araca ait veri veri tabanında güncellendi.");
	
	}

	@Override
	public Result delete(DeleteCarMaintenanceRequest deleteCarMaintenanceRequest) {
		CarMaintenance carMaintenance = this.modelMapperService.forRequest().map(deleteCarMaintenanceRequest, CarMaintenance.class);
		 this.carMaintenanceDao.delete(carMaintenance);
		 return new SuccessResult(deleteCarMaintenanceRequest.getId()+" 'li araç bakım bilgileri veri tabanından silindi.");
	
	}


	@Override
	public DataResult<GetCarMaintenanceDto> getByCarMaintenanceId(int carMaintenanceId) {
		 
		CarMaintenance result = this.carMaintenanceDao.getByid(carMaintenanceId);
		
		GetCarMaintenanceDto response = this.modelMapperService.forDto().map(result, GetCarMaintenanceDto.class);
		
		return new SuccessDataResult<GetCarMaintenanceDto>(response);
	}


	

}

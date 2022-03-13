package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.carMaintenance.ListCarMaintenanceDto;
import com.turkcell.rentacar.business.dtos.rental.GetRentalDto;
import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;
import com.turkcell.rentacar.business.requests.rental.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.DeleteRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.RentalDao;
import com.turkcell.rentacar.entites.concretes.Rental;

@Service
public class RentalManager implements RentalService{

	private RentalDao rentalDao;
	private CarMaintenanceManager carMaintenanceService;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao,ModelMapperService modelMapperService,@Lazy CarMaintenanceManager carMaintenanceService) {
		
		this.carMaintenanceService = carMaintenanceService;
		this.rentalDao=rentalDao;
		this.modelMapperService=modelMapperService;
	}
	
	@Override
	public DataResult<List<ListRentalDto>> getall() {
		var result = this.rentalDao.findAll();
		List<ListRentalDto> response = result.stream().map(rental->this.modelMapperService.forDto()
														.map(rental, ListRentalDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response, "Rental Listelendi");
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws BusinessException {
		
		checkIfCarIsInMaintenance(createRentalRequest.getCarId());
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Rental is Created.");
	}

	@Override
	public DataResult<GetRentalDto> getById(int rentalId) {
		Rental result = this.rentalDao.getById(rentalId);
		GetRentalDto response = this.modelMapperService.forDto().map(result, GetRentalDto.class);
		return new SuccessDataResult<GetRentalDto>(response,"getById");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		return new SuccessResult("Rental.Added");
		
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		
		checkIfRentExists(deleteRentalRequest.getId());
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalDao.delete(rental);
		return new SuccessResult("Rental.Deleted");
	}

	@Override
	public DataResult<List<ListRentalDto>> getByCar_carId(int carId) {
		
		List<Rental> result = this.rentalDao.getByCar_carId(carId);
        List<ListRentalDto> response = result.stream()
                .map(rentCar -> this.modelMapperService.forDto().map(rentCar, ListRentalDto.class))
                .collect(Collectors.toList());
        return new SuccessDataResult<List<ListRentalDto>>(response, "Rental.Listed");
       
	
	}
	
	 private void checkIfCarIsInMaintenance(int carId) throws BusinessException {
		 	
		 List<ListCarMaintenanceDto> maintenances = this.carMaintenanceService.getByCarId(carId).getData();
	         
		 for (ListCarMaintenanceDto listCarMaintenanceDtos : maintenances) {
			if(listCarMaintenanceDtos.getReturnDate()==null) {
				throw new BusinessException("Car is in maintenance.");
			}
		}
	        
	    }
	 
	 private void checkIfRentExists(int rentalId) throws BusinessException {
	        if (!rentalDao.existsById(rentalId)){
	            throw new BusinessException("Rent does not exist with id: ' "+rentalId+" '.");
	        }
	        
	    }
	 
//	private boolean checkIfCitiesAreTheSame(int rentalId) {
//		if(!rentalDao.getById(rentalId).getRentCity().equals(rentalDao.getById(rentalId).getReturnCity())) {
//			return true;
//		}else {
//			return false;
//		}
//	}

}

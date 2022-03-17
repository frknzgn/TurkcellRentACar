package com.turkcell.rentacar.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
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
import com.turkcell.rentacar.entites.concretes.Car;
import com.turkcell.rentacar.entites.concretes.OrderedAdditionalService;
import com.turkcell.rentacar.entites.concretes.Rental;

@Service
public class RentalManager implements RentalService{

	private RentalDao rentalDao;
	private CarService carService;
	private CarMaintenanceService carMaintenanceService;
	private  OrderedAdditionalServiceService orderedAdditionalServiceService;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public RentalManager(RentalDao rentalDao,ModelMapperService modelMapperService,@Lazy CarMaintenanceService carMaintenanceService, @Lazy CarService carService, @Lazy OrderedAdditionalServiceService orderedAdditionalServiceService) {
		
		this.carService = carService;
		this.carMaintenanceService = carMaintenanceService;
		this.rentalDao=rentalDao;
		this.orderedAdditionalServiceService = orderedAdditionalServiceService;
		this.modelMapperService=modelMapperService;
		
	}
	
	
	@Override
	public Result add(CreateRentalRequest createRentalRequest) throws BusinessException {
		
		checkIfCarIsInMaintenance(createRentalRequest.getCarId());
	
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		
		int CarRentMilage = this.modelMapperService.forDto().map(this.carService.getByCarId(createRentalRequest.getCarId()), Car.class).getMilage();
		rental.setRentMilage(CarRentMilage);
		
		this.rentalDao.save(rental);
		
		rental.setRentalTotalPrice(calRentedTotal(rental.getRentalId()));
		
		return new SuccessResult("Rental.Created");
		
	}
	
	
	@Override
	public DataResult<List<ListRentalDto>> getall() {
		
		var result = this.rentalDao.findAll();
		List<ListRentalDto> response = result.stream().map(rental->this.modelMapperService.forDto().map(rental, ListRentalDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListRentalDto>>(response, "Rental.Listed");
		
	}
	
	@Override
	public DataResult<GetRentalDto> getById(int rentalId) {
		
		checkIfRentExists(rentalId);
		
		Rental result = this.rentalDao.getById(rentalId);
		GetRentalDto response = this.modelMapperService.forDto().map(result, GetRentalDto.class);
		
		return new SuccessDataResult<GetRentalDto>(response,"Rental.GetById");
		
	}
	
	@Override
	public DataResult<List<ListRentalDto>> getByCar_carId(int carId) {
		
		checkIfCarExist(carId);
		
		List<Rental> result = this.rentalDao.getByCar_carId(carId);
        List<ListRentalDto> response = result.stream()
                				.map(rentCar -> this.modelMapperService.forDto().
                						map(rentCar, ListRentalDto.class)).collect(Collectors.toList());
        
        return new SuccessDataResult<List<ListRentalDto>>(response, "Rental.Listed");
        
	}

	
	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {
		
		checkIfRentExists(updateRentalRequest.getRentalId());
		
		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		this.rentalDao.save(rental);
		
		updateRentCarReturnMilage(updateRentalRequest.getRentalId(), updateRentalRequest.getRentReturnMilage());
		
		return new SuccessResult("Rental.Added");
		
	}

	
	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
		
		checkIfRentExists(deleteRentalRequest.getId());
		
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalDao.delete(rental);
		
		return new SuccessResult("Rental.Deleted");
		
	}
	
	
	private void checkIfCarIsInMaintenance(int carId) throws BusinessException {
		 	
		 List<ListCarMaintenanceDto> maintenances = this.carMaintenanceService.getByCarId(carId).getData();
	         
		 for (ListCarMaintenanceDto listCarMaintenanceDtos : maintenances) {
			if(listCarMaintenanceDtos.getReturnDate().equals(null)) {
				throw new BusinessException("Car is in maintenance.");
			}
		}
	        
	}
	 
	private void checkIfRentExists(int rentalId) throws BusinessException {
	        if (!rentalDao.existsById(rentalId)){
	            throw new BusinessException("Rent does not exist with id: ' "+rentalId+" '.");
	        }
	        
	}
	 
	private void checkIfCarExist(int carId) {
		 
		 this.carService.getByCarId(carId);
		 
	}
	
	private double calRentedTotal(int id) {

        Rental rental = rentalDao.getById(id);

        long totalDays = ChronoUnit.DAYS.between(rental.getRentDate(), rental.getRentReturnDate());

        double carDailyPrice = rental.getCar().getDailyPrice();

        double OrderedAdditionalServicesDailyPrice = this.orderedAdditionalServiceService.calDailyTotal(rental.getOrderedAdditionalServices());

        double dailyTotal = carDailyPrice + OrderedAdditionalServicesDailyPrice;

        return (dailyTotal * totalDays) + checkCityIds(rental);
    }

    private double checkCityIds(Rental rental) {
    	
        if (rental.getRentCity().getCityId()!=rental.getRentReturnCity().getCityId()) {
        	
            return 750.0;
            
        }
        
        return 0.0;
        
    }
    
    private Result updateRentCarReturnMilage(int rentalId,int rentReturnMilage) {
    	
    	this.rentalDao.getById(rentalId).getCar().setMilage(rentReturnMilage);    	
    	return new SuccessResult("Car.Milage.Updated");
    	
    	
    }
}
	 	
	 


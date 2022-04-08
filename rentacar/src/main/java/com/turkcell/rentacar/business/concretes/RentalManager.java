package com.turkcell.rentacar.business.concretes;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CarMaintenanceService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.CityService;
import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.abstracts.PaymentService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.rental.GetRentalDto;
import com.turkcell.rentacar.business.dtos.rental.ListRentalDto;
import com.turkcell.rentacar.business.requests.payment.CreateExtraPaymentRequest;
import com.turkcell.rentacar.business.requests.rental.CreateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.DeleteRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalRequest;
import com.turkcell.rentacar.business.requests.rental.UpdateRentalReturnRequest;
import com.turkcell.rentacar.core.constants.Messages;
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
	 private ModelMapperService modelMapperService;
	 private CarMaintenanceService carMaintenanceService;
	 private CarService carService;
	 private CityService cityService;
	 private IndividualCustomerService individualCustomerService;
	 private CorporateCustomerService corporateCustomerService;
	 private CustomerService customerService;
	 private PaymentService paymentService;


	    @Autowired
	    public RentalManager(RentalDao rentalDao, ModelMapperService modelMapperService,@Lazy CarMaintenanceService carMaintenanceService,
	                          @Lazy CarService carService,@Lazy CityService cityService, @Lazy CorporateCustomerService corporateCustomerService,
	                          @Lazy IndividualCustomerService individualCustomerService, @Lazy CustomerService customerService, @Lazy PaymentService paymentService) {
	        
	    	this.rentalDao = rentalDao;
	        this.modelMapperService = modelMapperService;
	        this.carMaintenanceService = carMaintenanceService;
	        this.carService = carService;
	        this.cityService = cityService;
	        this.corporateCustomerService = corporateCustomerService;
	        this.individualCustomerService = individualCustomerService;
	        this.customerService = customerService;
	        this.paymentService = paymentService;
	        
	    }

	    @Override
	    public DataResult<Integer> addForIndividual(CreateRentalRequest createRentalRequest) throws BusinessException {
	    	
	        this.carMaintenanceService.checkIfCarMaintenanceReturnDateByCarId(createRentalRequest.getCarId());
	        checkIfRentalReturnDateByCarId(createRentalRequest.getCarId());
	        this.individualCustomerService.checkIfIndividualCustomerIdExists(createRentalRequest.getCustomer_UserId());
	        this.carService.checkCarExist(createRentalRequest.getCarId());
	        this.cityService.checkIfCityIdExists(createRentalRequest.getRentCityId());
	        this.cityService.checkIfCityIdExists(createRentalRequest.getRentReturnCityId());

	        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);


	        rental.setRentMilage(this.carService.getByCarId(rental.getCar().getCarId()).getData().getMilage());

	        rental.setRentalId(0);
	        Rental returnRentcar = this.rentalDao.save(rental);

	        return new SuccessDataResult<Integer>(returnRentcar.getRentalId(), Messages.RENTAL_ADDED);
	    }

	    @Override
	    public Result addForCorporate(CreateRentalRequest createRentalRequest) throws BusinessException {
	    	
	        this.carMaintenanceService.checkIfCarMaintenanceReturnDateByCarId(createRentalRequest.getCarId());
	        checkIfRentalReturnDateByCarId(createRentalRequest.getCarId());
	        this.corporateCustomerService.checkIfCorporateCustomerIdExists(createRentalRequest.getCustomer_UserId());
	        this.carService.checkCarExist(createRentalRequest.getCarId());
	        this.cityService.checkIfCityIdExists(createRentalRequest.getRentCityId());
	        this.cityService.checkIfCityIdExists(createRentalRequest.getRentReturnCityId());

	        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

	        rental.setRentMilage(this.carService.getByCarId(rental.getCar().getCarId()).getData().getMilage());

	        rental.setRentalId(0);
	        this.rentalDao.save(rental);

	        return new SuccessResult(Messages.RENTAL_ADDED);
	    }

	    @Override
	    public Result update(UpdateRentalRequest updateRentalRequest) throws BusinessException {
	    	
	        checkIfRentalExists(updateRentalRequest.getRentalId());
	        checkIfRentalReturnDateByCarId(updateRentalRequest.getCarId());
	        this.carService.checkCarExist(updateRentalRequest.getCarId());
	        this.carMaintenanceService.checkIfCarMaintenanceReturnDateByCarId(updateRentalRequest.getCarId());
	        this.cityService.checkIfCityIdExists(updateRentalRequest.getRentCityId());
	        this.cityService.checkIfCityIdExists(updateRentalRequest.getRentReturnCityId());
	        this.customerService.checkIfCustomerIdExist(updateRentalRequest.getCustomerId());


	        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
	        this.rentalDao.save(rental);

	        return new SuccessResult(Messages.RENTAL_UPDATED);
	        
	    }
	    

	    @Override
	    public Result updateForReturnCar(UpdateRentalReturnRequest updateRentalReturnRequest) throws BusinessException {
	    	
	        this.carService.checkCarExist(updateRentalReturnRequest.getCarId());
	        Rental rental = this.rentalDao.getRentalByCar_CarIdOrderByRentalIdDesc(updateRentalReturnRequest.getCarId()).get(0);
	        int daysBetweenTwoDates = (int) ChronoUnit.DAYS.between(rental.getDateOfReceipt(), updateRentalReturnRequest.getRentReturnDate());
	        if (daysBetweenTwoDates > 0) {
	            CreateExtraPaymentRequest createExtraPaymentRequest = new CreateExtraPaymentRequest(rental.getRentalId(),
	                    rental.getCustomer().getUserId(),
	                    rental.getCar().getCarId(),
	                    rental.getDateOfReceipt(),
	                    updateRentalReturnRequest.getRentReturnDate(),
	                    updateRentalReturnRequest.getCardDetailForPaymentRequest());

	            this.paymentService.extraDaysRentCarPayment(createExtraPaymentRequest);

	        }
	        
	        isCarReturnedFromRent(updateRentalReturnRequest.getCarId(), updateRentalReturnRequest.getRentReturnMilage());
	        rental.setRentReturnDate(updateRentalReturnRequest.getRentReturnDate());
	        rental.setRentReturnMilage(updateRentalReturnRequest.getRentReturnMilage());
	        this.rentalDao.save(rental);
	        
	        return new SuccessResult(Messages.RENTAL_UPDATED);
	        
	    }

	    @Override
	    public Result delete(DeleteRentalRequest deleteRentalRequest) throws BusinessException {
	    	
	        checkIfRentalExists(deleteRentalRequest.getRentalId());

	        Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
	        this.rentalDao.deleteById(rental.getRentalId());
	        
	        return new SuccessResult(Messages.RENTAL_DELETED);
	    }

	    
	    @Override
	    public DataResult<List<ListRentalDto>> getAll() {
	    	
	        List<Rental> result = this.rentalDao.findAll();
	        
	        List<ListRentalDto> response = result.stream()
	                .map(rentCar -> this.modelMapperService.forDto().
	                		map(rentCar, ListRentalDto.class)).collect(Collectors.toList());
	        
	        return new SuccessDataResult<List<ListRentalDto>>(response, Messages.RENTAL_LISTED);
	        
	    }

	    @Override
	    public DataResult<List<ListRentalDto>> getRentCarsByCarId(int carId) throws BusinessException {
	        this.carService.checkCarExist(carId);

	        List<Rental> result = this.rentalDao.getRentalByCar_CarId(carId);
	        
	        List<ListRentalDto> response = result.stream()
	                .map(rentCar -> this.modelMapperService.forDto().
	                		map(rentCar, ListRentalDto.class)).collect(Collectors.toList());
	        
	        return new SuccessDataResult<List<ListRentalDto>>(response, Messages.RENTAL_GETBY_CAR_ID);
	        
	    }

	    @Override
	    public DataResult<GetRentalDto> getById(int rentalId) throws BusinessException {
	    	
	        checkIfRentalExists(rentalId);

	        Rental result = this.rentalDao.getById(rentalId);
	        GetRentalDto response = this.modelMapperService.forDto().map(result, GetRentalDto.class);
	        
	        return new SuccessDataResult<GetRentalDto>(response, Messages.RENTAL_LISTED);
	    }

	    @Override
	    public void checkIfRentalReturnDateByCarId(int carId) throws BusinessException {
	    	
	        if (this.rentalDao.getRentalByCar_CarIdAndRentReturnDateIsNull(carId) != null) {
	        	
	            throw new BusinessException(Messages.RENTAL_STILL);
	        }
	    }
	    
	    @Override
	    public void checkIfRentalExists(int rentCarId) throws BusinessException {
	    	
	        if (!this.rentalDao.existsByRentalId(rentCarId)) {
	        	
	            throw new BusinessException(Messages.RENTAL_NOT_EXİST);
	            
	        }
	    }


	    private void isCarReturnedFromRent(int carId, double returnDistance) throws BusinessException {
	    	
	        if (returnDistance > 0) {

	            this.carService.isCarReturnFromRent(carId, returnDistance);
	            
	        }
	    }
	    
	    @Override
	    public double totalRentalDailyPriceAndDifferntCityCalculator(Rental rental) throws BusinessException {
	    	
	        this.cityService.checkIfCityIdExists(rental.getRentReturnCity().getCityId());
	        this.cityService.checkIfCityIdExists(rental.getRentReturnCity().getCityId());
	        this.carService.checkCarExist(rental.getCar().getCarId());

	        double differentCity = rental.getRentReturnCity().getCityId() == rental.getRentCity().getCityId() ? 0 : 750;

	        double carDayOfDailyPriceTotalFee = this.carService.totalCarDailyPriceCalculator
	                (rental.getCar().getCarId(), rental.getRentDate(), rental.getDateOfReceipt());

	        return differentCity + carDayOfDailyPriceTotalFee;

	    }


		
	
}
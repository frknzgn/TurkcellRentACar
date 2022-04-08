 package com.turkcell.rentacar.business.concretes;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.car.GetCarDto;
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
public class CarManager implements CarService {

    private CarDao carDao;
    private ModelMapperService modelMapperService;
    private BrandService brandService;
    private ColorService colorService;

    @Autowired
    public CarManager(CarDao carDao, ModelMapperService modelMapperService,@Lazy ColorService colorService,@Lazy BrandService brandService) {
       
    	this.carDao = carDao;
        this.modelMapperService = modelMapperService;
        this.colorService = colorService;
        this.brandService = brandService;
        
    }

    @Override
    public Result add(CreateCarRequest createCarRequest) throws BusinessException {

        this.brandService.checkIfBrandIdExists(createCarRequest.getBrandId());
        this.colorService.checkIfColorIdExists(createCarRequest.getColorId());

        Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
        car.setCarId(0);
        this.carDao.save(car);

        return new SuccessResult(Messages.CAR_ADDED);
    }
    

    @Override
    public DataResult<List<ListCarDto>> getAll() {
    	
        List<Car> result = this.carDao.findAll();
        List<ListCarDto> response = result.stream()
                .map(car -> this.modelMapperService.forDto()
                		.map(car, ListCarDto.class)).collect(Collectors.toList());
        
        return new SuccessDataResult<List<ListCarDto>>(response,Messages.CAR_LİSTED);

    }

    @Override
    public DataResult<GetCarDto> getByCarId(int carId) throws BusinessException {
    	
        checkCarExist(carId);

        Car car = this.carDao.getById(carId);
        GetCarDto response = this.modelMapperService.forRequest().map(car, GetCarDto.class);
        
        return new SuccessDataResult<GetCarDto>(response,Messages.CAR_GETBY_ID);
    }

    @Override
    public Result update(UpdateCarRequest updateCarRequest) throws BusinessException {
    	
        checkCarExist(updateCarRequest.getCarId());
        
        this.brandService.checkIfBrandIdExists(updateCarRequest.getBrandId());
        this.colorService.checkIfColorIdExists(updateCarRequest.getColorId());

        Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
        this.carDao.save(car);

        return new SuccessResult(Messages.CAR_UPDATED);
    }

    @Override
    public Result delete(DeleteCarRequest deleteCarRequest) throws BusinessException {
    	
        checkCarExist(deleteCarRequest.getCarId());

        Car car = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
        this.carDao.deleteById(car.getCarId());
        
        return new SuccessResult(Messages.CAR_DELETED);
        
    }

    @Override
    public DataResult<List<ListCarDto>> getCarByDailyPriceLessThanEqual(double dailyPrice) {
    	
        List<Car> result = this.carDao.getCarByDailyPriceLessThanEqual(dailyPrice);
        List<ListCarDto> response = result .stream()
        		.map(car -> this.modelMapperService.forDto()
        				.map(car, ListCarDto.class)) .collect(Collectors.toList());
        
        return new SuccessDataResult<List<ListCarDto>>(response,Messages.CAR_LİSTEDBY_DAİLYPRİCELESSTHANEQUAL);
    }

    @Override
    public DataResult<List<ListCarDto>> getAllPaged(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        
        List<Car> result = this.carDao.findAll(pageable).getContent();
        
        List<ListCarDto> response = result .stream()
                .map(car -> this.modelMapperService.forDto().
                		map(car, ListCarDto.class)).collect(Collectors.toList());
        
        return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LİSTEDBY_PAGED);
        
    }

    @Override
    public DataResult<List<ListCarDto>> getAllSorted(Sort.Direction direction) {


        Sort sorted = Sort.by(direction, "dailyPrice");
        
        List<Car> result = this.carDao.findAll(sorted);
        
        List<ListCarDto> response = result .stream()
                .map(car -> this.modelMapperService.forDto().
                		map(car, ListCarDto.class)).collect(Collectors.toList());
        
        return new SuccessDataResult<List<ListCarDto>>(response, Messages.CAR_LİSTED);
        
        
    }

    public void checkCarExist(int carId) throws BusinessException{

        if (!this.carDao.existsById(carId)) {
        	
            throw new BusinessException( Messages.CAR_NOT_EXİST);
            
        }
    }
    

    @Override
    public void isCarReturnFromRent(int carId, double rentReturnMilage) throws BusinessException {
    	
        checkCarExist(carId);
        
        Car car = this.carDao.getById(carId);
        
        if (car.getMilage() < rentReturnMilage) {
        	
            car.setMilage(rentReturnMilage);
            
            this.carDao.save(car);
            
        } else {
        	
            throw new BusinessException(Messages.CAR_RETURN_DISTANCE_ERROR);
            
        }
    }

    @Override
    public double totalCarDailyPriceCalculator(int carId, LocalDate dateOfIssue, LocalDate dateOfReceipt) {
    	
        int daysBetweenTwoDates = (int) ChronoUnit.DAYS.between(dateOfIssue, dateOfReceipt);
        return this.carDao.getById(carId).getDailyPrice() * daysBetweenTwoDates;
        
    }

}

package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.orderedadditionalservice.ListOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.OrderedAdditionalServiceDao;
import com.turkcell.rentacar.entites.concretes.OrderedAdditionalService;

@Service
public class OrderedAdditionalServiceManager implements OrderedAdditionalServiceService {

	 private OrderedAdditionalServiceDao orderedAdditionalServiceDao;
	    private ModelMapperService modelMapperService;
	    private AdditionalServiceService additionalServiceService;
	    private RentalService rentalService;

	    @Autowired
	    public OrderedAdditionalServiceManager(OrderedAdditionalServiceDao orderedAdditionalServiceDao, ModelMapperService modelMapperService,
	    											@Lazy AdditionalServiceService additionalServiceService, @Lazy RentalService rentalService) {
	        
	    	this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
	        this.modelMapperService = modelMapperService;
	        this.additionalServiceService = additionalServiceService;
	        this.rentalService = rentalService;	  

	    }

	    
	    @Override
	    public Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) throws BusinessException {
	    	
	        this.rentalService.checkIfRentalExists(createOrderedAdditionalServiceRequest.getRentalId());
	        this.additionalServiceService.checkIfAdditionIdExists(createOrderedAdditionalServiceRequest.getAdditionalServiceId());
	        
	        OrderedAdditionalService response = this.modelMapperService.forRequest()
	        		.map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
	        
	        response.setOrderedAdditionalServiceId(0);
	        this.orderedAdditionalServiceDao.save(response);
	        
	        return new SuccessResult(Messages.ORDERED_ADDITIONAL_SERVICE_ADDED);
	        
	    }

	    @Override
	    public Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalServiceRequest) throws BusinessException {
	    	
	        checkIfOrderedAdditionalIdExists(updateOrderedAdditionalServiceRequest.getOrderedAdditionalServiceId());
	        this.rentalService.checkIfRentalExists(updateOrderedAdditionalServiceRequest.getRentalId());
	        this.additionalServiceService.checkIfAdditionIdExists(updateOrderedAdditionalServiceRequest.getAdditionalServiceId());
	        
	        OrderedAdditionalService response = this.modelMapperService.forRequest().
	        											map(updateOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
	        
	        this.orderedAdditionalServiceDao.save(response);
	        
	        return new SuccessResult(Messages.ORDERED_ADDITIONAL_SERVICE_UPDATED);
	        
	    }
	    

	    @Override
	    public Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalServiceRequest) throws BusinessException {
	    	
	        checkIfOrderedAdditionalIdExists(deleteOrderedAdditionalServiceRequest.getOrderedAdditionalServiceId());

	        OrderedAdditionalService response = this.modelMapperService.forRequest().
	        									map(deleteOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
	        
	        this.orderedAdditionalServiceDao.deleteById(response.getOrderedAdditionalServiceId());
	        
	        return new SuccessResult(Messages.ORDERED_ADDITIONAL_SERVICE_DELETED);
	        
	    }

	    @Override
	    public DataResult<List<ListOrderedAdditionalServiceDto>> getAll() {
	    	
	        List<OrderedAdditionalService> response = this.orderedAdditionalServiceDao.findAll();
	        
	        List<ListOrderedAdditionalServiceDto> result = response .stream()
	                .map(orderedAdditional -> this.modelMapperService.forDto().
	                		map(orderedAdditional, ListOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
	        
	        return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(result, Messages.ORDERED_ADDITIONAL_SERVICE_LISTED);
	        
	    }
	    

	    @Override
	    public DataResult<List<ListOrderedAdditionalServiceDto>> getById(int orderedAdditionalId) throws BusinessException {
	    	
	        checkIfOrderedAdditionalIdExists(orderedAdditionalId);

	        List<OrderedAdditionalService> response = this.orderedAdditionalServiceDao.findAllByOrderedAdditionalServiceId(orderedAdditionalId);
	        List<ListOrderedAdditionalServiceDto> result = response.stream()
	                .map(orderedAdditional -> this.modelMapperService.forDto().
	                		map(orderedAdditional, ListOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
	        
	        return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(result, Messages.ORDERED_ADDITIONAL_SERVICE_LISTED);
	        
	    }

	    @Override
	    public void addAdditionals(int rentId, List<Integer> additionalList) throws BusinessException {
	    	
	        this.rentalService.checkIfRentalExists(rentId);

	        for (Integer additionalId : additionalList) {
	            this.additionalServiceService.checkIfAdditionIdExists(additionalId);
	            
	            CreateOrderedAdditionalServiceRequest createOrderedAdditionalRequest = new CreateOrderedAdditionalServiceRequest(rentId, additionalId);
	           
	            this.add(createOrderedAdditionalRequest);
	            
	        }
	    }

	    private void checkIfOrderedAdditionalIdExists(int orderedAdditionalId) throws BusinessException {
	    	
	        if (!this.orderedAdditionalServiceDao.existsById(orderedAdditionalId)) {
	        	
	            throw new BusinessException(Messages.ORDERED_ADDITIONAL_SERVICE_NOT_FOUND);
	        }
	    }

	    @Override
	    public List<Integer> getAdditionalServiceIdsByRentalId(int rentId) {
	    	
	        List<OrderedAdditionalService> orderedAdditionals = this.orderedAdditionalServiceDao.getByRentalRentalId(rentId);
	        return orderedAdditionals.stream()
	                .map(additional -> this.modelMapperService.forDto().
	                		map(additional.getAdditionalService().getAdditionalServiceId(), Integer.class)).collect(Collectors.toList());
	        
	    }


	    public double totalAdditionalDailyPriceCalculator(List<Integer> additionalList) throws BusinessException {
	        double total = 0;
	        for (Integer integer : additionalList) {
	            total += this.additionalServiceService.getById(integer).getData().getAdditionalServicePrice();
	        }
	        return total;
	    }

}
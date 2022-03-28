package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.OrderedAdditionalServiceService;
import com.turkcell.rentacar.business.abstracts.RentalService;
import com.turkcell.rentacar.business.dtos.orderedadditionalservice.GetOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.orderedadditionalservice.ListOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.UpdateOrderedAdditionalServiceRequest;
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
	//private RentalService rentalService;
	
	@Autowired
	public OrderedAdditionalServiceManager(RentalService rentalService,OrderedAdditionalServiceDao orderedAdditionalServiceDao,ModelMapperService modelMapperService) {
		
		this.modelMapperService = modelMapperService;
		this.orderedAdditionalServiceDao = orderedAdditionalServiceDao;
		//this.rentalService = rentalService;
		
	}
	
	@Override
	public Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest) {
		
		OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().
				map(createOrderedAdditionalServiceRequest, OrderedAdditionalService.class);
		
		
		
	
		
		this.orderedAdditionalServiceDao.save(orderedAdditionalService);
		
		return new SuccessResult("OrderedAdditionalService.Added");
		
	}

	/*
	 * private void checkIfRentalExists(int rentalId) {
	 * 
	 * if (this.rentalService.getById(rentalId).equals(null)) {
	 * 
	 * throw new BusinessException("Cannot find a rented with this Id."); }
	 * 
	 * }
	 */

	@Override
	public DataResult<List<ListOrderedAdditionalServiceDto>> getall() throws BusinessException {
		
		var result = this.orderedAdditionalServiceDao.findAll();
		
		List<ListOrderedAdditionalServiceDto> response = result.stream().
				map(orderedService->this.modelMapperService.forDto().
						map(orderedService, ListOrderedAdditionalServiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListOrderedAdditionalServiceDto>>(response, "OrderedAdditionalService.Listed");
		
	}

	@Override
	public DataResult<GetOrderedAdditionalServiceDto> getById(int id) throws BusinessException {
		
		var result = this.orderedAdditionalServiceDao.getById(id);
		
		GetOrderedAdditionalServiceDto response = this.modelMapperService.forDto().map(result, GetOrderedAdditionalServiceDto.class);
		
		return new SuccessDataResult<GetOrderedAdditionalServiceDto>(response, "OrderedAdditionalService.GetById");
		
	}

	@Override
	public Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditonalServiceRequest) {
		
		OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(updateOrderedAdditonalServiceRequest, OrderedAdditionalService.class);
		
		this.orderedAdditionalServiceDao.save(orderedAdditionalService);
		
		return new SuccessResult("OrderedAdditionalService.Updated");
	
	}

	@Override
	public Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditonalServiceRequest) {
		
		OrderedAdditionalService orderedAdditionalService = this.modelMapperService.forRequest().map(deleteOrderedAdditonalServiceRequest, OrderedAdditionalService.class);
		
		this.orderedAdditionalServiceDao.delete(orderedAdditionalService);
		
		return new SuccessResult("OrderedAdditionalService.Deleted");
		
	}
	


	@Override
	public Double calculateAdditionalServiceCost(List<OrderedAdditionalService> orderedAdditionalServices) {
		
		double dailyTotal = 0;

        for (OrderedAdditionalService orderedAdditionalService : orderedAdditionalServices) {

            dailyTotal += orderedAdditionalService.getQuantity() * orderedAdditionalService.getAdditionalService().getAdditionalServicePrice();
        }

        return dailyTotal;
	}

}

package com.turkcell.rentacar.business.abstracts;

import java.util.List;
import java.util.Set;

import com.turkcell.rentacar.business.dtos.orderedadditionalservice.GetOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.orderedadditionalservice.ListOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entites.concretes.OrderedAdditionalService;

public interface OrderedAdditionalServiceService {
	
	Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalServiceRequest);
	
	DataResult<List<ListOrderedAdditionalServiceDto>> getall() throws BusinessException;
	DataResult<GetOrderedAdditionalServiceDto> getById(int id) throws BusinessException;
	
	Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditonalServiceRequest) throws BusinessException;
	
	Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditonalServiceRequest) throws BusinessException;
	
	Double calDailyTotal(Set<OrderedAdditionalService> orderedAdditionalServices);
}

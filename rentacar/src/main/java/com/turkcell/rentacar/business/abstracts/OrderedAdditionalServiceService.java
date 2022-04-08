package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.orderedadditionalservice.ListOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface OrderedAdditionalServiceService {
	
	Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalRequest) throws BusinessException;

    Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalRequest) throws BusinessException;

    Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalRequest) throws BusinessException;

    DataResult<List<ListOrderedAdditionalServiceDto>> getAll();

    DataResult<List<ListOrderedAdditionalServiceDto>> getById(int OrderedAdditionalId) throws BusinessException;

    void addAdditionals(int rentId, List<Integer> additionalList) throws BusinessException;

    List<Integer> getAdditionalServiceIdsByRentalId(int rentId);
    
}

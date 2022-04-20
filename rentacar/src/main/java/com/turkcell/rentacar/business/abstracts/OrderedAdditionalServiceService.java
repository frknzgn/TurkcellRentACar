package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.orderedadditionalservice.ListOrderedAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.CreateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.DeleteOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.business.requests.orderedadditionalservice.UpdateOrderedAdditionalServiceRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface OrderedAdditionalServiceService {
	
	Result add(CreateOrderedAdditionalServiceRequest createOrderedAdditionalRequest);
	
	DataResult<List<ListOrderedAdditionalServiceDto>> getAll();
    DataResult<List<ListOrderedAdditionalServiceDto>> getById(int OrderedAdditionalId);
    List<Integer> getAdditionalServiceIdsByRentalId(int rentId);
    void addAdditionals(int rentId, List<Integer> additionalList);
    
    Result update(UpdateOrderedAdditionalServiceRequest updateOrderedAdditionalRequest);

    Result delete(DeleteOrderedAdditionalServiceRequest deleteOrderedAdditionalRequest);

   
   

    
    
}

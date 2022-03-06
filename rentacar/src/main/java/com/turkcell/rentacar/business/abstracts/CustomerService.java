package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.customer.GetCustomerDto;
import com.turkcell.rentacar.business.dtos.customer.ListCustomerDto;
import com.turkcell.rentacar.business.requests.customer.CreateCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.DeleteCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.UpdateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CustomerService {
	
	DataResult<List<ListCustomerDto>> getall();
	Result add(CreateCustomerRequest createCustomerRequest) throws BusinessException;
	DataResult<GetCustomerDto> getById(int id) ;
	Result update(UpdateCustomerRequest updateCustomerRequest);
	Result delete(DeleteCustomerRequest deleteCustomerRequest) throws BusinessException;

}

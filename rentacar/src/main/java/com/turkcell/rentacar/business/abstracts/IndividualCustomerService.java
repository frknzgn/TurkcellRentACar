package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.individualCustomer.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.turkcell.rentacar.business.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface IndividualCustomerService {
	
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws BusinessException;
	
	DataResult<List<ListIndividualCustomerDto>> getall();
	DataResult<GetIndividualCustomerDto> getById(int id) ;
	
	Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest);
	
	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) throws BusinessException;
	
	void checkIfIndividualCustomerIdExists(int IndividualCustomerId) throws BusinessException;
	
}
package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.corporateCustomer.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.turkcell.rentacar.business.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.corporateCustomer.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface CorporateCustomerService extends CustomerService {
	
	Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException;
	
	DataResult<List<ListCorporateCustomerDto>> getall();
	DataResult<GetCorporateCustomerDto> getById(int id) ;
	
	Result update(UpdateCorporateCustomerRequest updateCustomerRequest);
	
	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException;

}
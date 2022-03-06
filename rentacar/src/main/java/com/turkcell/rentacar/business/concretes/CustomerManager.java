package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.customer.GetCustomerDto;
import com.turkcell.rentacar.business.dtos.customer.ListCustomerDto;
import com.turkcell.rentacar.business.requests.customer.CreateCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.DeleteCustomerRequest;
import com.turkcell.rentacar.business.requests.customer.UpdateCustomerRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CustomerDao;
import com.turkcell.rentacar.entites.concretes.Customer;

@Service
public class CustomerManager implements CustomerService {
	
	private CustomerDao customerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CustomerManager(ModelMapperService modelMapperService,CustomerDao customerDao) {
		this.customerDao=customerDao;
		this.modelMapperService =modelMapperService;
	}
	
	@Override
	public DataResult<List<ListCustomerDto>> getall() {
		
		List<Customer> result = customerDao.findAll();
		
		List<ListCustomerDto> response = result.stream().map(customer->this.modelMapperService.forDto().
															map(customer, ListCustomerDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCustomerDto>>(response, "Customer.Listed.");
		}

	@Override
	public Result add(CreateCustomerRequest createCustomerRequest) throws BusinessException {
		
		Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
		this.customerDao.save(customer);
		return new SuccessResult("Customer.Added.");
	}

	@Override
	public DataResult<GetCustomerDto> getById(int id) {
		
		Customer customer = this.customerDao.getById(id);
		GetCustomerDto response = this.modelMapperService.forDto().map(customer, GetCustomerDto.class);
		return new SuccessDataResult<GetCustomerDto>(response);
	}

	@Override
	public Result update(UpdateCustomerRequest updateCustomerRequest) {
		
		Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
		this.customerDao.save(customer);
		return new SuccessResult("Customer.Updated.");
	}

	@Override
	public Result delete(DeleteCustomerRequest deleteCustomerRequest) throws BusinessException {
		
		Customer customer = this.modelMapperService.forRequest().map(deleteCustomerRequest, Customer.class);
		this.customerDao.delete(customer);
		return new SuccessResult("Customer.Deleted.");
	}

}

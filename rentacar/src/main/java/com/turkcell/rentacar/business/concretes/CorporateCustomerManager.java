
package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
import com.turkcell.rentacar.business.abstracts.CustomerService;
import com.turkcell.rentacar.business.dtos.corporateCustomer.GetCorporateCustomerDto;
import com.turkcell.rentacar.business.dtos.corporateCustomer.ListCorporateCustomerDto;
import com.turkcell.rentacar.business.requests.corporateCustomer.CreateCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.corporateCustomer.DeleteCorporateCustomerRequest;
import com.turkcell.rentacar.business.requests.corporateCustomer.UpdateCorporateCustomerRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CorporateCustomerDao;
import com.turkcell.rentacar.entites.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService{
	
	private CustomerService customerService;
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService, CustomerService customerService) {
		
		this.customerService = customerService;
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
		
	}
	
	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
		
		//customerService.checkIfEmailExist(createCorporateCustomerRequest.getEmail());		
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		
	
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.CUSTOMER_ADDED);
		
	}
	

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getall() {
		
		var result = this.corporateCustomerDao.findAll();	
		List<ListCorporateCustomerDto> response = result.stream().
										map(customer->this.modelMapperService.forDto().
												map(customer, ListCorporateCustomerDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCorporateCustomerDto>>(response,Messages.CUSTOMER_LİSTED);
		
	}

	@Override
	public DataResult<GetCorporateCustomerDto> getById(int id) {
		
		checkIfCorporateCustomerIdExists(id);
		
		CorporateCustomer result = this.corporateCustomerDao.getById(id);		
		GetCorporateCustomerDto response = this.modelMapperService.forDto().map(result, GetCorporateCustomerDto.class);
		
		return new SuccessDataResult<GetCorporateCustomerDto>(response, Messages.CUSTOMER_GETBY_ID);
	}
	

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCustomerRequest) {
		
		checkIfCorporateCustomerIdExists(updateCustomerRequest.getCustomerId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCustomerRequest, CorporateCustomer.class);		
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.CUSTOMER_UPDATED);
		
	}
	

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
		
		checkIfCorporateCustomerIdExists(deleteCorporateCustomerRequest.getCustomerId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(deleteCorporateCustomerRequest, CorporateCustomer.class);		
		this.corporateCustomerDao.delete(corporateCustomer);
		
		return new SuccessResult(Messages.CUSTOMER_DELETED);
		
	}
	
	public void checkEmailExist(String email) {
		
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();
		
		for (CorporateCustomer corporateCustomer : corporateCustomers) {
			if(corporateCustomer.getEmail().toLowerCase().matches(email)) {
				
				throw new BusinessException(Messages.CUSTOMER_EMAİL_EXİST);
				
			}
			
		}
	}
	
	public final void checkCustomerExist(String taxNumber) {
		
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();
		
		for (CorporateCustomer corporateCustomer : corporateCustomers) {
			if(corporateCustomer.getTaxNumber() == taxNumber) {
				
				throw new BusinessException(Messages.CUSTOMER_EXİST);
				
			}
		}
				
	}
	

	@Override
	public void checkIfCorporateCustomerIdExists(int CorporateCustomerId) throws BusinessException {
		
		if(!this.corporateCustomerDao.existsById(CorporateCustomerId)) {
			
			throw new BusinessException(Messages.CUSTOMER_NOT_EXİST);
		
		}
	}

}

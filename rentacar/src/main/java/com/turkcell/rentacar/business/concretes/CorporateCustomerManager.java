package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.CorporateCustomerService;
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
	
	private CorporateCustomerDao corporateCustomerDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public CorporateCustomerManager(CorporateCustomerDao corporateCustomerDao, ModelMapperService modelMapperService) {
		
		this.corporateCustomerDao = corporateCustomerDao;
		this.modelMapperService = modelMapperService;
		
	}
	
	@Override
	public Result add(CreateCorporateCustomerRequest createCorporateCustomerRequest) throws BusinessException {
		
		checkCustomerExist(createCorporateCustomerRequest.getTaxNumber());
		checkEmailExist(createCorporateCustomerRequest.getEmail());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(createCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.CUSTOMERADDED);
		
	}
	

	@Override
	public DataResult<List<ListCorporateCustomerDto>> getall() {
		
		var result = this.corporateCustomerDao.findAll();	
		List<ListCorporateCustomerDto> response = result.stream().
										map(customer->this.modelMapperService.forDto().
										map(customer, ListCorporateCustomerDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListCorporateCustomerDto>>(response,Messages.CUSTOMERLİSTED);
		
	}

	@Override
	public DataResult<GetCorporateCustomerDto> getById(int id) {
		
		checkCustomerNotExist(id);
		
		CorporateCustomer result = this.corporateCustomerDao.getById(id);		
		GetCorporateCustomerDto response = this.modelMapperService.forDto().map(result, GetCorporateCustomerDto.class);
		
		return new SuccessDataResult<GetCorporateCustomerDto>(response, Messages.CUSTOMERLİSTED);
	}
	

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCustomerRequest) {
		
		checkCustomerNotExist(updateCustomerRequest.getId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(updateCustomerRequest, CorporateCustomer.class);		
		this.corporateCustomerDao.save(corporateCustomer);
		
		return new SuccessResult(Messages.CUSTOMERUPDATED);
		
	}
	

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) throws BusinessException {
		
		checkCustomerNotExist(deleteCorporateCustomerRequest.getId());
		
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(deleteCorporateCustomerRequest, CorporateCustomer.class);		
		this.corporateCustomerDao.delete(corporateCustomer);
		
		return new SuccessResult(Messages.CUSTOMERDELETED);
		
	}
	
	public void checkEmailExist(String email) {
		
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();
		
		for (CorporateCustomer corporateCustomer : corporateCustomers) {
			if(corporateCustomer.getEmail().toLowerCase().matches(email)) {
				
				throw new BusinessException(Messages.CUSTOMEREMAİLEXİST);
				
			}
			
		}
	}
	
	public final void checkCustomerExist(String taxNumber) {
		
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerDao.findAll();
		
		for (CorporateCustomer corporateCustomer : corporateCustomers) {
			if(corporateCustomer.getTaxNumber() == taxNumber) {
				
				throw new BusinessException(Messages.CUSTOMEREXİST);
				
			}
		}
				
	}
	
	public final void checkCustomerNotExist(int customerId) {
		
		if(this.corporateCustomerDao.getByCustomerId(customerId)==null) {
			
			throw new BusinessException(Messages.CUSTOMERNOTEXİST);
			
		}
		
	}

}

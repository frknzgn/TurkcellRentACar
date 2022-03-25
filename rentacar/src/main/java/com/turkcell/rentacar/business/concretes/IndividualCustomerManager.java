package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.IndividualCustomerService;
import com.turkcell.rentacar.business.dtos.individualCustomer.GetIndividualCustomerDto;
import com.turkcell.rentacar.business.dtos.individualCustomer.ListIndividualCustomerDto;
import com.turkcell.rentacar.business.requests.individualCustomer.CreateIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.DeleteIndividualCustomerRequest;
import com.turkcell.rentacar.business.requests.individualCustomer.UpdateIndividualCustomerRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.IndividualCustomerDao;
import com.turkcell.rentacar.entites.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	
	private IndividualCustomerDao individualCustomerDao;
	private ModelMapperService modelMapperService;
	
	public IndividualCustomerManager(IndividualCustomerDao individualCustomerDao, ModelMapperService modelMapperService) {
		
		this.individualCustomerDao = individualCustomerDao;
		this.modelMapperService = modelMapperService;
	}
	
	
	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) {
		
		checkCustomerExist(createIndividualCustomerRequest.getIndividualCustomerNationalityId());
		checkEmailExist(createIndividualCustomerRequest.getEmail());
		checkNationalityIdValid(createIndividualCustomerRequest.getIndividualCustomerNationalityId());
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(createIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.CUSTOMERADDED);
		
	}
	

	@Override
	public DataResult<List<ListIndividualCustomerDto>> getall() {
		
		var result = this.individualCustomerDao.findAll();
		 List<ListIndividualCustomerDto> response = result.stream().
				 							map(customer->this.modelMapperService.forDto().
				 								map(customer, ListIndividualCustomerDto.class)).collect(Collectors.toList());
		 
		 return new SuccessDataResult<List<ListIndividualCustomerDto>>(response, Messages.CUSTOMERLİSTED);
		
	}

	@Override
	public DataResult<GetIndividualCustomerDto> getById(int individualCustomerId) {
		
		 checkCustomerNotExist(individualCustomerId);
		
		IndividualCustomer individualCustomer = this.individualCustomerDao.getById(individualCustomerId);
		GetIndividualCustomerDto response = this.modelMapperService.forDto().map(individualCustomer, GetIndividualCustomerDto.class);
		
		return new SuccessDataResult<GetIndividualCustomerDto>(response, Messages.CUSTOMERGETBYID);
		
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		
		 checkCustomerNotExist(updateIndividualCustomerRequest.getIndividualCustomerId());
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(updateIndividualCustomerRequest, IndividualCustomer.class);		
		this.individualCustomerDao.save(individualCustomer);
		
		return new SuccessResult(Messages.CUSTOMERUPDATED);
		
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		
		 checkCustomerNotExist(deleteIndividualCustomerRequest.getIndividualCustomerId());
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest().map(deleteIndividualCustomerRequest, IndividualCustomer.class);		
		this.individualCustomerDao.delete(individualCustomer);
		
		return new SuccessResult(Messages.CUSTOMERDELETED);
	}
	
	
	public final void checkCustomerExist(String individualCustomerNationalityId) {
		
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();
		
		for (IndividualCustomer individualCustomer : individualCustomers) {
			if(individualCustomer.getIndividualCustomerNationalityId().matches(individualCustomerNationalityId)) {
				
				throw new BusinessException(Messages.CUSTOMEREXİST);
				
			}
		}
		
	}
	
	private void checkNationalityIdValid(String individualCustomerNationalityId) {
		
		if(individualCustomerNationalityId.length() != 11 || individualCustomerNationalityId == null) {
			
			throw new BusinessException(Messages.NATIONALITYIDNOTVALID);
		}
		
	}
	
	public final void checkCustomerNotExist(int customerId) {
		
		if(this.individualCustomerDao.getByCustomerId(customerId)==null) {
			
			throw new BusinessException(Messages.CUSTOMERNOTEXİST);
		}
	}
	
	public final void checkEmailExist(String email) {
		
		List<IndividualCustomer> individualCustomers = this.individualCustomerDao.findAll();
		
		for (IndividualCustomer individualCustomer : individualCustomers) {
			if(individualCustomer.getEmail().toLowerCase().matches(email)) {
				
				throw new BusinessException(Messages.CUSTOMEREMAİLEXİST);
				
			}
			
		}
	}

}

package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.AdditionalServiceService;
import com.turkcell.rentacar.business.dtos.additionalService.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.addtionalService.CreateAdditonalServiceRequest;
import com.turkcell.rentacar.business.requests.addtionalService.DeleteAdditonalServiceRequest;
import com.turkcell.rentacar.business.requests.addtionalService.UpdateAdditonalServiceRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.AdditionalServiceDao;
import com.turkcell.rentacar.entites.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
	
	private AdditionalServiceDao additionalServiceDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public AdditionalServiceManager(ModelMapperService modelMapperService,AdditionalServiceDao additionalServiceDao) {
		this.additionalServiceDao = additionalServiceDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public Result add(CreateAdditonalServiceRequest createAdditonalServiceRequest) {
		
		checkAdditionalServiceExit(createAdditonalServiceRequest.getAdditionalServiceName());
		
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditonalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessResult("AdditionalService.Added");
		
	}
	

	@Override
	public DataResult<List<ListAdditionalServiceDto>> getall() throws BusinessException {
		
		var result = this.additionalServiceDao.findAll();
		List<ListAdditionalServiceDto> response = result.stream().
					map(service->this.modelMapperService.forDto().
						map(service, ListAdditionalServiceDto.class)).collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListAdditionalServiceDto>>(response, "AdditionalService.Listed");
		
	}

	@Override
	public DataResult<GetAdditionalServiceDto> getById(int additionalServiceId) {
		
		checkAdditionalServiceNotExist(additionalServiceId);
		
		AdditionalService additionalService = this.additionalServiceDao.getByAdditionalServiceId(additionalServiceId);
		GetAdditionalServiceDto response = this.modelMapperService.forDto().map(additionalService, GetAdditionalServiceDto.class);
		
		return new SuccessDataResult<GetAdditionalServiceDto>(response, "AdditionalService.Get");
		
	}
	

	@Override
	public Result update(UpdateAdditonalServiceRequest updateAdditonalServiceRequest) throws BusinessException {
		
		checkAdditionalServiceNotExist(updateAdditonalServiceRequest.getAdditionalServiceId());
		
		AdditionalService additionalService = this.modelMapperService.forRequest().map(updateAdditonalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.save(additionalService);
		
		return new SuccessResult("AdditionalService.Updated");
		
	}
	

	@Override
	public Result delete(DeleteAdditonalServiceRequest deleteAdditonalServiceRequest) throws BusinessException {
		
		checkAdditionalServiceNotExist(deleteAdditonalServiceRequest.getAdditionalServiceId());
		
		AdditionalService additionalService = this.modelMapperService.forRequest().map(deleteAdditonalServiceRequest, AdditionalService.class);
		this.additionalServiceDao.delete(additionalService);
		
		return new SuccessResult("AdditionalService.Deleted");
	
	}
	
	
	private void checkAdditionalServiceExit(String additionalServiceName) {
		
		List<AdditionalService> additionalServices = this.additionalServiceDao.findAll();
		for (AdditionalService additionalService : additionalServices) {
			if(additionalService.getAdditionalServiceName().toLowerCase().matches(additionalServiceName.toLowerCase())) {
				
				throw new BusinessException(Messages.ADDITIONALSERVICEEXİST);
			}
		}	
	}
	
	private void checkAdditionalServiceNotExist(int additionalServiceId) {
		
		if(this.additionalServiceDao.getByAdditionalServiceId(additionalServiceId) == null) {
			
			throw new BusinessException(Messages.ADDITIONALSERVICENOTEXİST);
			
		}
	}
	
}

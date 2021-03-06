package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.additionalService.GetAdditionalServiceDto;
import com.turkcell.rentacar.business.dtos.additionalService.ListAdditionalServiceDto;
import com.turkcell.rentacar.business.requests.addtionalService.CreateAdditonalServiceRequest;
import com.turkcell.rentacar.business.requests.addtionalService.DeleteAdditonalServiceRequest;
import com.turkcell.rentacar.business.requests.addtionalService.UpdateAdditonalServiceRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

public interface AdditionalServiceService {
	
	Result add(CreateAdditonalServiceRequest createAdditonalServiceRequest);
	
	DataResult<List<ListAdditionalServiceDto>> getall() throws BusinessException;
	DataResult<GetAdditionalServiceDto> getById(int additionalServiceId) ;
	
	Result update(UpdateAdditonalServiceRequest updateAdditonalServiceRequest) throws BusinessException;
	
	Result delete(DeleteAdditonalServiceRequest deleteAdditonalServiceRequest) throws BusinessException;
	
	void checkIfAdditionIdExists(int additionalServiceId) throws BusinessException;
	double totalAdditionalServiceFeeCalculator(List<Integer> additionalServiceList);
	
}

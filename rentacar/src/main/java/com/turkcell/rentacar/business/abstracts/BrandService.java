package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


public interface BrandService {
	
	DataResult<List<ListBrandDto>> getall() throws BusinessException;
	Result add(CreateBrandRequest createBrandRequest) throws BusinessException;
	DataResult<GetBrandDto> getById(int brandId) throws BusinessException;
	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
	Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;

}

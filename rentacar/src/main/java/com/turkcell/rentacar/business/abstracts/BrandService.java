package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.brand.GetBrandDto;
import com.turkcell.rentacar.business.dtos.brand.ListBrandDto;
import com.turkcell.rentacar.business.requests.brand.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brand.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.brand.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


public interface BrandService {
	
	Result add(CreateBrandRequest createBrandRequest);
	
	DataResult<List<ListBrandDto>> getall() throws BusinessException;
	DataResult<GetBrandDto> getById(int brandId) throws BusinessException;
	
	Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
	
	Result delete(DeleteBrandRequest deleteBrandRequest) throws BusinessException;

}

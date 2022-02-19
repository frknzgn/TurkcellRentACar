package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;


public interface BrandService {
	
	List<ListBrandDto> getall();
	void add(CreateBrandRequest createBrandRequest) throws BusinessException;
	GetBrandDto getById(int brandId);
	void update(UpdateBrandRequest updateBrandRequest) throws BusinessException;
	void delete(DeleteBrandRequest deleteBrandRequest);

}

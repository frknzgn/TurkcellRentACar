package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entites.entites.Brand;

public class BrandManager implements BrandService{
	
	private BrandDao branDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao branDao, ModelMapperService modelMapperService) {
		this.branDao = branDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListBrandDto> getall() {
		var result = this.branDao.findAll();
		
		List<ListBrandDto> response = result.stream()
				.map(brand -> this.modelMapperService.forDto()
				.map(brand,ListBrandDto.class)).collect(Collectors.toList());
				
				return response;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) throws BusinessException {
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
		
		checkBrandNameExist(brand);
		this.branDao.save(brand);		
		
	}


	@Override
	public GetBrandDto getById(int brandId) {
		var result = this.branDao.getById(brandId);
		GetBrandDto response = this.modelMapperService.forDto().map(result,GetBrandDto.class);
		return response;
	}
	
	private void checkBrandNameExist(Brand brand) throws BusinessException {
		
		if(this.branDao.existsByBrandName(brand.getName())) {
			throw new BusinessException("Brand Already exist.");
		}
	}

}

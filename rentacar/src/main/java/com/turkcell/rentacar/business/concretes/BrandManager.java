package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.dtos.ListBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entites.concretes.Brand;

@Service
public class BrandManager implements BrandService{
	
	private BrandDao brandDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public BrandManager(BrandDao branDao, ModelMapperService modelMapperService) {
		this.brandDao = branDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public List<ListBrandDto> getall() {
		var result = this.brandDao.findAll();
		
		List<ListBrandDto> response = result.stream()
				.map(brand -> this.modelMapperService.forDto()
				.map(brand,ListBrandDto.class)).collect(Collectors.toList());
				
				return response;
	}

	@Override
	public void add(CreateBrandRequest createBrandRequest) throws BusinessException {
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
		
		//checkBrandNameExist(createBrandRequest.getBrandName());
		this.brandDao.save(brand);		
		
	}


	@Override
	public GetBrandDto getById(int brandId) {
		var result = this.brandDao.getById(brandId);
		GetBrandDto response = this.modelMapperService.forDto().map(result,GetBrandDto.class);
		return response;
	}
	
	
	@Override
	public void update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
		
		if(!checkIfEmpty(brand.getBrandId())) {
			this.brandDao.save(brand);
		}else {
			
		}
	}
	

	@Override
	public void delete(DeleteBrandRequest deleteBrandRequest) {
		
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);
		
	}
	
	private void checkBrandNameExist(String brandName) throws BusinessException {
			
			if(!this.brandDao.getByBrandName(brandName).getBrandName().equals(brandName)) {
				throw new BusinessException("Brand Already exist.");
			}
		}
	
	private boolean checkIfEmpty(int brandId) throws BusinessException {
		
		if(this.brandDao.getByBrandId(brandId)==null) {
			throw new BusinessException("This Id is empty");
		}
		return true;
	}
}

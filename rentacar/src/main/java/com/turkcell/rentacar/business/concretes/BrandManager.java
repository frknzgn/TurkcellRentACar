package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.brand.GetBrandDto;
import com.turkcell.rentacar.business.dtos.brand.ListBrandDto;
import com.turkcell.rentacar.business.requests.brand.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.brand.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.brand.UpdateBrandRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
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
	public Result add(CreateBrandRequest createBrandRequest) {
		
		checkBrandNameExist(createBrandRequest.getBrandName());
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
		this.brandDao.save(brand);
		
		return new SuccessResult(Messages.BRAND_ADDED);
		
	}
	
	
	@Override
	public DataResult<List<ListBrandDto>> getall() throws BusinessException {
		
		var result = this.brandDao.findAll();
		List<ListBrandDto> response = result.stream()
							.map(brand -> this.modelMapperService.forDto()
									.map(brand,ListBrandDto.class)).collect(Collectors.toList());
				
		return new SuccessDataResult<List<ListBrandDto>>(response, Messages.BRAND_LİSTED);
		
	}
	
	@Override
	public DataResult<GetBrandDto> getById(int brandId) {
		
		checkIfBrandIdExists(brandId);
	//Defencive programming Ya id yoksa internal serverla logları kullanıcıya iletir
		var result = this.brandDao.getById(brandId);
		GetBrandDto response = this.modelMapperService.forDto().map(result,GetBrandDto.class);
		
		return new SuccessDataResult<GetBrandDto>(response, Messages.BRAND_GETBY_ID);
		
	}
	
	
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		checkBrandNameExist(updateBrandRequest.getBrandName());
		checkIfBrandIdExists(updateBrandRequest.getBrandId());
	
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
		this.brandDao.save(brand);
		
		return new SuccessResult(Messages.BRAND_UPTADED);
		
	}
	
	
	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		
		checkIfBrandIdExists(deleteBrandRequest.getBrandId());
		
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);
		return new SuccessResult(Messages.BRAND_DELETED);
		
	}
	
	
	
	private void checkBrandNameExist(String brandName) {
		
		List<Brand> brands = this.brandDao.findAll();
		
		for (Brand brand : brands) {
			if(brand.getBrandName().toLowerCase().matches(brandName.toLowerCase())) {
				
				throw new BusinessException(Messages.BRAND_EXİST);
				
			}
		}		
	}


	@Override
	public void checkIfBrandIdExists(int brandId) {
		
		if(!this.brandDao.existsByBrandId(brandId)) {
			
			throw new BusinessException(Messages.BRAND_NOT_EXİST);
			
		}
		
	}
}


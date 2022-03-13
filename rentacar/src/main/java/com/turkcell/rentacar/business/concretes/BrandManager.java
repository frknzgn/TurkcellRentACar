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
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
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
	public DataResult<List<ListBrandDto>> getall() throws BusinessException {
		checkIfListEmpty();
		var result = this.brandDao.findAll();
		List<ListBrandDto> response = result.stream()
				.map(brand -> this.modelMapperService.forDto()
				.map(brand,ListBrandDto.class)).collect(Collectors.toList());
				
				return new SuccessDataResult<List<ListBrandDto>>(response, "Data getirildi.");
	}

	

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {
		
		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest,Brand.class);
		this.brandDao.save(brand);
		return new SuccessResult("Marka veritabanına eklendi.");
		
	}


	@Override
	public DataResult<GetBrandDto> getById(int brandId) {
		
		//Defencive programming Ya id yoksa internal serverla logları kullanıcıya iletir
		var result = this.brandDao.getByBrandId(brandId);
		if(result==null) {
			return new ErrorDataResult<GetBrandDto>("Geçersiz Id");
		}
		GetBrandDto response = this.modelMapperService.forDto().map(result,GetBrandDto.class);
		return new SuccessDataResult<GetBrandDto>(response,"ID ile marka getirildi.");
	}
	
	
	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) throws BusinessException {
		
		Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest,Brand.class);
		
		if(!checkIfEmpty(brand.getBrandId())) {

			this.brandDao.save(brand);
			return new SuccessResult("Güncellendi.");
		}else {
			
			return new ErrorResult("Id boş.");
			
		}
	}
	

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {
		
		Brand brand = this.modelMapperService.forRequest().map(deleteBrandRequest, Brand.class);
		this.brandDao.delete(brand);
		return new SuccessResult("Silindi.");
		
	}
	
	private void checkIfListEmpty() throws BusinessException {
		if(this.brandDao.findAll().isEmpty()) {
			throw new BusinessException("List is empty.");
		}
	}
	
	private boolean checkIfEmpty(int brandId) throws BusinessException {
		
		if(this.brandDao.getByBrandId(brandId)==null) {
			throw new BusinessException("This Id is empty");
		}
		return true;
	}
	
	private boolean checkBrandNameExist(String brandName){
		
		List<Brand> brands = this.brandDao.findAll();
		
		for (Brand brand : brands) {
			if(brand.getBrandName().toLowerCase()==brandName.toLowerCase()) {
				return false;
			}
		}
		return false;
	}
}

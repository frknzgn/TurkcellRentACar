package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentacar.entites.concretes.Color;

@Service
public class ColorManager implements ColorService {
	
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public DataResult<List<ListColorDto>> getall() {
		var result = this.colorDao.findAll();
			
		List<ListColorDto> response = result.stream()
						.map(color -> this.modelMapperService.forDto()
								.map(color,ListColorDto.class)).collect(Collectors.toList());			
		return new SuccessDataResult<List<ListColorDto>>(response, "Listelendi renkler.");
	}
	
	@Override
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest,Color.class);
		
		if(checkColorNameExist(color)) {
			this.colorDao.save(color);	
			return new SuccessResult("Eklendi");
		}else {
			return new ErrorResult("Renk mevcut ekleyemem.");
		}
			
		
	}

	@Override
	public DataResult<GetColorDto> getById(int colorId) {
		Color result = this.colorDao.getByColorId(colorId);
		GetColorDto response = this.modelMapperService.forDto().map(result, GetColorDto.class);
		return new SuccessDataResult<GetColorDto>(response, "Id ye göre renk getirildi.");
	}
	
	private boolean checkColorNameExist(Color color){
			
			if(this.colorDao.existsById(color.getColorId())) {
				return false;
			}
			return true;
		}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		return new SuccessResult("güncellendi.");
		
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		
		Color color = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		this.colorDao.delete(color);
		return new SuccessResult("Silindi.");
		
	}
		
}


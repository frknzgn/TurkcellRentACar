package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.color.GetColorDto;
import com.turkcell.rentacar.business.dtos.color.ListColorDto;
import com.turkcell.rentacar.business.requests.color.CreateColorRequest;
import com.turkcell.rentacar.business.requests.color.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.color.UpdateColorRequest;
import com.turkcell.rentacar.core.constants.Messages;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
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
	public Result add(CreateColorRequest createColorRequest) throws BusinessException {
		
		checkColorNameExist(createColorRequest.getColorName());
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest,Color.class);
		this.colorDao.save(color);
		
		return new SuccessResult(Messages.COLOR_ADDED);
		
	}
	
	
	@Override
	public DataResult<List<ListColorDto>> getall() {
		
		var result = this.colorDao.findAll();
		List<ListColorDto> response = result.stream()
						.map(color -> this.modelMapperService.forDto()
								.map(color,ListColorDto.class)).collect(Collectors.toList());			
		return new SuccessDataResult<List<ListColorDto>>(response, Messages.COLORS_LISTED);
		
	}

	@Override
	public DataResult<GetColorDto> getById(int colorId) {
		
		checkIfColorIdExists(colorId);
		
		Color result = this.colorDao.getByColorId(colorId);
		GetColorDto response = this.modelMapperService.forDto().map(result, GetColorDto.class);
		
		return new SuccessDataResult<GetColorDto>(response, Messages.COLOR_GETBY_ID);
		
	}
	
	
	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		checkIfColorIdExists(updateColorRequest.getColorId()); 
			
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorDao.save(color);
		
		return new SuccessResult(Messages.COLOR_UPTADED);
		
	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		
		checkIfColorIdExists(deleteColorRequest.getColorId());
		
		Color color = this.modelMapperService.forRequest().map(deleteColorRequest, Color.class);
		this.colorDao.delete(color);
		
		return new SuccessResult(Messages.COLOR_DELETED);
		
	}
	
	public void checkIfColorIdExists(int colorId) {
		
		if(this.colorDao.getByColorId(colorId)==null) {
			
			throw new BusinessException(Messages.COLOR_NOT_EXİST);
			
		}
		
	}
	
	private void checkColorNameExist(String colorName){
			
		List<Color> colors = this.colorDao.findAll();
		
		for (Color color : colors) {
			if(color.getColorName().toLowerCase().matches(colorName.toLowerCase())) {
				
				throw new BusinessException(Messages.COLOR_EXİST);
				
			}
		}
			
	}
	
}


package com.turkcell.rentacar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentacar.entites.entites.Color;


public class ColorManager implements ColorService {
	
	private ColorDao colorDao;
	private ModelMapperService modelMapperService;
	
	@Autowired
	public ColorManager(ColorDao colorDao, ModelMapperService modelMapperService) {
		this.colorDao = colorDao;
		this.modelMapperService = modelMapperService;
	}
	
	@Override
	public List<ListColorDto> getall() {
		var result = this.colorDao.findAll();
			
		List<ListColorDto> response = result.stream()
						.map(color -> this.modelMapperService.forDto()
								.map(color,ListColorDto.class)).collect(Collectors.toList());			
		return response;
	}
	
	@Override
	public void add(CreateColorRequest createColorRequest) throws BusinessException {
		
		Color color = this.modelMapperService.forRequest().map(createColorRequest,Color.class);
		
		checkColorNameExist(color);
		this.colorDao.save(color);		
		
	}

	@Override
	public GetColorDto getById(int colorId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void checkColorNameExist(Color color) throws BusinessException {
			
			if(this.colorDao.existsByColorName(color.getName())) {
				throw new BusinessException("Color Already exist.");
			}
		}
		
	}


package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.dtos.ListColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;

public interface ColorService {
	
	List<ListColorDto> getall();
	void add(CreateColorRequest createColorRequest) throws BusinessException;
	GetColorDto getById(int colorId);
	void update(UpdateColorRequest updateColorRequest);
	void delete(DeleteColorRequest deleteColorRequest);
	
}

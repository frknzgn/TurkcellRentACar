package com.turkcell.rentacar.business.abstracts;

import java.util.List;

import com.turkcell.rentacar.business.dtos.color.GetColorDto;
import com.turkcell.rentacar.business.dtos.color.ListColorDto;
import com.turkcell.rentacar.business.requests.color.CreateColorRequest;
import com.turkcell.rentacar.business.requests.color.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.color.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BusinessException;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;


public interface ColorService {
	
	Result add(CreateColorRequest createColorRequest) throws BusinessException;
	
	DataResult<List<ListColorDto>> getall() throws BusinessException;
	DataResult<GetColorDto> getById(int colorId) throws BusinessException;
	
	Result update(UpdateColorRequest updateColorRequest) throws BusinessException;
	
	Result delete(DeleteColorRequest deleteColorRequest) throws BusinessException;

	void checkIfColorIdExists(int colorId);
	
}

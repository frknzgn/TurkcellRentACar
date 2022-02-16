package com.turkcell.rentacar.core.utilities.mapping.abstracts;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
	
	ModelMapper forDto();
	ModelMapper forRequest();

}

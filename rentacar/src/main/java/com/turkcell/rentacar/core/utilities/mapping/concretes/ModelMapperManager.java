package com.turkcell.rentacar.core.utilities.mapping.concretes;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.turkcell.rentacar.core.utilities.mapping.abstracts.ModelMapperService;

public class ModelMapperManager implements ModelMapperService{

	private ModelMapper modelMapper;
	
	public ModelMapperManager(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forDto() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE);
		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD);
		return this.modelMapper;
	}

}

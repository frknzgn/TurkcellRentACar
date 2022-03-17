package com.turkcell.rentacar.business.dtos.orderedadditionalservice;

import com.turkcell.rentacar.entites.concretes.AdditionalService;
import com.turkcell.rentacar.entites.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOrderedAdditionalServiceDto {
	
	private int id;
    private int quantity;
    private double bill;
    private AdditionalService additionalService;
    private Rental rental;
    
}
package com.turkcell.rentacar.business.dtos.rental;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalDto {
	
	private int rentalId;
	
    private LocalDate dateOfIssue;
    private LocalDate dateOfReceipt;
    
    private LocalDate rentReturnDate;
    
    private double rentMilage;
    private double rentReturMilage;
    
    private String brandName;
    
    private String description;
    
    private String rentCity;
    private String rentReturnCity;
	
}

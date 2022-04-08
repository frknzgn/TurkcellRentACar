package com.turkcell.rentacar.business.dtos.invoice;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceDto {
	
	private int invoiceId;

	private LocalDate creatingDate;
	
    private double totalPrice;
    
    private int customer_CustomerId;
    private int rental_RentalId;
    private LocalDate rental_RentalDateOfIssue;
    private LocalDate rental_RentalDateOfReceipt;
	
}
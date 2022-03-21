package com.turkcell.rentacar.business.dtos.invoice;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.business.dtos.customer.ListCustomerDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetInvoiceDto {
	
	private int invoiceId;
	private String invoiceNumber;
	private LocalDate creatingDate;
	private LocalDate rentDate;
	private int totalRentDay;
	private double total;
	private List<ListCustomerDto> customers;
	
}
package com.turkcell.rentacar.business.requests.invoice;

import java.time.LocalDate;
import java.util.List;

import com.turkcell.rentacar.entites.concretes.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	private LocalDate creatingDate;
	private LocalDate rentDate;
	private int totalRentDay;
	private double total;
	private List<Customer> customers;
	
}

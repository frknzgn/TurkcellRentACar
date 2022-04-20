package com.turkcell.rentacar.business.requests.invoice;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInvoiceRequest {
	
	@NotNull
	private int rentalId;
	
	@NotNull
	private int customerId;
	
	@NotNull
	@Min(0)
	private double totalPrice;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate creatingDate;
	
}

package com.turkcell.rentacar.business.requests.invoice;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class UpdateInvoiceRequest {
	
	@NotNull
	private int invoiceId;
	
	@NotNull
	private int rentalId;
	
	@NotNull
	private int customerId;
	
	@NotNull
	@Min(0)
	private double totalPrice;
	
	@NotNull
	private LocalDate creatingDate;
	
}

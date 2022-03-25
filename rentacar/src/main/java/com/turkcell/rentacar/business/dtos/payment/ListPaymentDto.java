package com.turkcell.rentacar.business.dtos.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListPaymentDto {
	
	private int paymentId;
	private int paymentInvoiceId;
	private int orderedAdditionalServiceId;
	private String cardHolder;
	
}

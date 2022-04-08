package com.turkcell.rentacar.business.requests.payment;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePaymentRequest {
	
	 private int paymentId;

	 @NotNull
	 @Min(0)
	 private double totalPaymentAmount;
	 
	 @NotNull
	 private int invoiceId;

	
}
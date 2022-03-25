package com.turkcell.rentacar.business.requests.payment;

import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	
	@Nullable
	private int invoiceId;
	
	@Nullable
	private int orderedAdditionalServiceId;
	
	@NotNull
	private String cardOwnerName;
	
	@NotNull
	private String cardNumber;
	
	@NotNull
	private int cardCvvNumber;
	
	@Nullable
	private boolean carDetailSaveOption;
	
}


package com.turkcell.rentacar.business.requests.cardDetail;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardDetailForPaymentRequest {
	
	@NotNull
	private String cardHolder;
	
	@NotNull
    @Size(min = 16, max = 16)
	private String cardNo;
	
	@NotNull
    @Size(min = 3, max = 3)
	private String cardCvv;
	
	@NotNull
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate cardExpirationDate;

}
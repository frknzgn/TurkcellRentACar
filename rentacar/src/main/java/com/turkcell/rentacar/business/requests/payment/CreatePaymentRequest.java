package com.turkcell.rentacar.business.requests.payment;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePaymentRequest {
	
	private int rentalId;


	private String cardHolder;
	
    @Size(min = 16, max = 16)
	private String cardNo;
	
    @Size(min = 3, max = 3)
	private String cardCvv;
	
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate cardExpirationDate;
	//private CreateCardDetailForPaymentRequest createCardDetailForPaymentRequest;
	
	private boolean saveCreditCard;
	
}



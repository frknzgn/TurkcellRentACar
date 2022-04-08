package com.turkcell.rentacar.business.requests.rental;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalReturnRequest {
	
	@NotNull
	@Positive
	private int carId;

	@NotNull
	private LocalDate rentReturnDate;
	
	@NotNull
	private int rentReturnMilage;
	
	private CreateCardDetailForPaymentRequest cardDetailForPaymentRequest;
	
}
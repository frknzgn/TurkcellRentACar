package com.turkcell.rentacar.business.requests.payment;

import java.time.LocalDate;

import com.turkcell.rentacar.business.requests.cardDetail.CreateCardDetailForPaymentRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateExtraPaymentRequest {

    private int rentalId;
    private int customerId;
    private int carId;
    private LocalDate dateOfReceipt;
    private LocalDate carReturnDate;
    private CreateCardDetailForPaymentRequest createCreditCardDetailForPaymentRequest;


}
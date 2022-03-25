package com.turkcell.rentacar.entites.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private int paymentId;
	
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	
	@OneToOne
	@JoinColumn(name = "rental_id")
	private Rental paymnetRental;
	
	@OneToOne
	@JoinColumn(name = "invoice_id")
	private Invoice paymentInvoice;
	
	@Column(name="card_holder_name")
	private String cardHolder;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="card_cvv_number")
	private int cardCvvNumber;
	
	@OneToOne
	@JoinColumn(name="ordered_additional_service_id")
	private OrderedAdditionalService orderedAdditionalService;
	

	
	
}

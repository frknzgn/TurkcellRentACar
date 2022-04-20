package com.turkcell.rentacar.entites.concretes;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "card_details")
public class CardDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_detail_id")
	private int cardDetailId;
	
	@Column(name = "card_holder")
	private String cardHolder;
	
	@Column(name = "cardNo")
	private String cardNo;
	
	@Column(name = "card_cvv")
	private String cardCvv;
	
	@Column(name = "card_expiration_date")
	@DateTimeFormat(pattern = "dd.MM.yyyy")
	private LocalDate cardExpirationDate;
	
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	
}

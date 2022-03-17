package com.turkcell.rentacar.entites.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
@Entity
@Table(name = "Invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private int invoiceId;
	
	@Column(name = "invoice_number")
	private String invoiceNumber;
	
	@Column(name = "creating_date")
	private LocalDate creatingDate;
	
	@Column(name = "rent_date")
	private LocalDate rentDate;
	
	@Column(name = "return_date")
	private LocalDate returnDate;
	
	@Transient
	private int totalRentDay;
	
	@Column(name = "total")
	private double totalInvoiceCost;
	
	@ManyToOne
	@JoinColumn(name = "invoices", nullable = false)
	private Customer customer;
	
//isimlendirme daha belirgin olmalı.
}

package com.turkcell.rentacar.entites.concretes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.turkcell.rentacar.entites.abstracts.User;

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
@Table(name="customers")
public class Customer extends User{
	
	@OneToMany(mappedBy = "rentalId",cascade = CascadeType.ALL)
	private List<Rental> rentals;
	
	@OneToMany(mappedBy = "invoiceId", cascade = CascadeType.ALL)
	private List<Invoice> invoices;
	
	//Customer base'ı kalmalı.
	//customer type olmalı(individual ve corporate)
}

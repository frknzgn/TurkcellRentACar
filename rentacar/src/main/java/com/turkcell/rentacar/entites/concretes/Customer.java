package com.turkcell.rentacar.entites.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.turkcell.rentacar.entites.abstracts.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
@PrimaryKeyJoinColumn(name = "customer_id",referencedColumnName = "user_id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends User{
	
	@Column(name = "customer_id",insertable = false,updatable = false)
	private int customerId;
	
	@Column(name = "registered_date")
	@CreationTimestamp
	private LocalDate registeredDate;
	
	
	@OneToMany(mappedBy = "customer")
    private List<Invoice> invoices;
	 
	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Rental> rentals;
	
	@OneToMany(mappedBy = "customer",cascade = {CascadeType.ALL})
	private List<CardDetail> carDetails;	
	
}

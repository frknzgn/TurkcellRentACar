package com.turkcell.rentacar.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "individual_customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class IndividualCustomer extends Customer {
	
	@Column(name = "individual_customer_firstName")
	private String firstName;
	
	@Column(name = "individual_customer_lastName")
	private String lastName;
}

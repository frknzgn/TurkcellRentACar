package com.turkcell.rentacar.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "individual_customers")
public class IndividualCustomer extends Customer {
	
	@Column(name = "individual_customer_first_name")
	private String individualCustomerFirstName;
	
	@Column(name = "individual_customer_last_name")
	private String individualCustomerLastName;
	
	@Column(name = "individual_customer_nationality_id")
	private Long nationalityId;
}

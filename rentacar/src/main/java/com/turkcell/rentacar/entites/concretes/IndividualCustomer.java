package com.turkcell.rentacar.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
@Table(name = "individual_customers")
@PrimaryKeyJoinColumn(name= "customer_id")
public class IndividualCustomer extends Customer {
	
	@Column(name = "individual_customer_firstName")
	private String firstName;
	
	@Column(name = "individual_customer_lastName")
	private String lastName;
	
	@Column(name = "individual_customer_nationality_id")
	private String individualCustomerNationalityId;
}

package com.turkcell.rentacar.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "corporate_customers")
public class CorporateCustomer extends Customer{
	
	@Column(name = "tax_number")
	private String taxNumber;
	
	@Column(name = "company_name")
	private String companyName;
	
}

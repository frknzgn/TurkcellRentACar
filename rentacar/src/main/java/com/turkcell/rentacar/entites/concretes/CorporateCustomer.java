package com.turkcell.rentacar.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "corporate_customers")
@PrimaryKeyJoinColumn(name = "corporate_customer_id", referencedColumnName = "customer_id")
public class CorporateCustomer extends Customer{
	
	@Column(name = "corporate_customer_id",insertable = false,updatable = false)
	private int customerId;
	
	@Column(name = "tax_number")
	private String taxNumber;
	
	@Column(name = "company_name")
	private String companyName;
	
}

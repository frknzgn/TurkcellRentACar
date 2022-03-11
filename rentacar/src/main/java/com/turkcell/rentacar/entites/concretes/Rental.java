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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rentals")
public class Rental {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rental_id")
	private int rentalId;
	
	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@Column(name="rent_date")
	private LocalDate rentDate;	
	
	@Column(name="return_date")
	private LocalDate returnDate;
	
	@OneToMany(mappedBy = "rental")
	private List<AdditionalService> additionalServices;
	

}

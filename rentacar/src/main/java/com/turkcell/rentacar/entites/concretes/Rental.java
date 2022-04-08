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
	
	@Column(name="rent_date")
	private LocalDate rentDate;	
	
	@Column(name="return_date")
	private LocalDate rentReturnDate;
	
	@Column(name = "date_of_receipt")
    private LocalDate dateOfReceipt;
	
	@Column(name = "rent_milage")
	private double rentMilage;
	
	@Column(name = "return_milage")
	private double rentReturnMilage;
	

	@ManyToOne
	@JoinColumn(name="car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "rent_city_id")
	private City rentCity;
	
	@ManyToOne
	@JoinColumn(name = "rent_return_city_id")
	private City rentReturnCity;
	
	
	
	@OneToMany(mappedBy = "rental")
    private List<OrderedAdditionalService> orderedAdditionalServices;
	
	@OneToMany(mappedBy = "rental")
    private List<Invoice> invoices;
	
}



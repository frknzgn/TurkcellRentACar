package com.turkcell.rentacar.entites.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
	private LocalDate rentReturnDate;
	
	@Column(name = "rent_milage")
	private int rentMilage;
	
	@Column(name = "return_milage")
	private int rentReturnMilage;
	
	@ManyToOne
	@JoinColumn(name = "rent_city_id")
	private City rentCity;
	
	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City rentReturnCity;

	
	@Column(name = "total_price")
	private double rentalTotalPrice;
	
	@OneToMany(mappedBy = "rental", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<OrderedAdditionalService> orderedAdditionalServices;
	
	@OneToOne(mappedBy = "rental")
	private Invoice invoice;
	
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(name = "ordered_additional_services",
//	joinColumns = @JoinColumn(name = "rental_id"),
//	inverseJoinColumns = @JoinColumn(name = "additional_service_id"))
//	private List<AdditionalService> rentalAdditionalServices;
}



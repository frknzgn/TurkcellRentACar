package com.turkcell.rentacar.entites.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.turkcell.rentacar.entites.abstracts.User;

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
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="rent_date")
	private LocalDate rentDate;	
	
	@Column(name="return_date")
	private LocalDate returnDate;
	
	@ManyToOne
	@JoinColumn(name = "rent_city_id")
	private City rentCity;
	
	@ManyToOne
	@JoinColumn(name = "return_city_id")
	private City returnCity;

	
	@Column(name = "total_price")
	private double totalPrice;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ordered_additional_services",
	joinColumns = @JoinColumn(name = "rental_id"),
	inverseJoinColumns = @JoinColumn(name = "additional_service_id"))
	private List<AdditionalService> rentalAdditionalServices;
}



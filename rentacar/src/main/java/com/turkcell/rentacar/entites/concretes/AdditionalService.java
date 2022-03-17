package com.turkcell.rentacar.entites.concretes;

import java.util.Set;

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
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="additionalservices")
public class AdditionalService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="additional_service_id")
	private int additionalServiceId;
	
	@Column(name = "name")
	private String additionalServiceName;
	
	@Column(name = "description")
	private String additionalServiceDescription;
	
	@Column(name = "price")
	private double additionalServicePrice;
	
	@ManyToOne
	@JoinColumn(name="rental_id")
	private Rental additionalServiceRental;
	
//	@ManyToMany(mappedBy = "rentalAdditionalServices")
//	private List<Rental> additionalServiceRentals;
	
	@OneToMany(mappedBy = "additionalService", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@ToString.Exclude
	private Set<OrderedAdditionalService> orderedAdditionalServices;

}
	

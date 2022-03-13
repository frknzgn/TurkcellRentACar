package com.turkcell.rentacar.entites.concretes;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.turkcell.rentacar.entites.abstracts.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="customers")
public class Customer extends User{
	
	@OneToMany(mappedBy = "rentalId")
	private List<Rental> rentals;


}

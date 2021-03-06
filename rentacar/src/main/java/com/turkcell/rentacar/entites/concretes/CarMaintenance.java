package com.turkcell.rentacar.entites.concretes;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carMaintenances")
public class CarMaintenance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int carMaintenanceId;
	
	@Column(name = "description")
	private String maintenanceDescription;
	
	
	@Column(name = "drop_date")
	private Date maintenanceDropDate;
	
	@Column(name = "return_date")
	private Date maintenanceReturnDate;
	

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
}

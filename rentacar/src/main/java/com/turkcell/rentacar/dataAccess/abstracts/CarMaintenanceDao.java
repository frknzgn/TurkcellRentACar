package com.turkcell.rentacar.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.CarMaintenance;

@Repository
public interface CarMaintenanceDao extends JpaRepository<CarMaintenance,Integer>{
	
	List<CarMaintenance> getAllByCarCarId(int carId);
	
	CarMaintenance getCarMaintenanceByCarCarIdAndMaintenanceReturnDateIsNull(int carId);
	
	boolean existsById(int carMaintenanceId);
	
}

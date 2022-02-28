package com.turkcell.rentacar.dataAccess.abstracts;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.turkcell.rentacar.entites.concretes.Car;

public interface CarDao extends JpaRepository<Car,Integer> {
	
	Car getByCarId(int carId);
	List<Car> getCarByDailyPriceLessThanEqual(double dailyPrice);
	
}

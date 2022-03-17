package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {
	
	City getById(int cityId);
	
	City findByCityName(String cityName);
	
}

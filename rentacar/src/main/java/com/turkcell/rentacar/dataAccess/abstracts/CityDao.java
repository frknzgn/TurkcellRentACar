package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.City;

@Repository
public interface CityDao extends JpaRepository<City, Integer> {
	
	City getByCityId(int cityId);
	
	City getByCityName(String cityName);
	
	boolean existsByCityId(int cityId);

    boolean existsByCityName(String cityName);
	
}

package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.Brand;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer>{
	
	Brand getByBrandId(int brandId); //geyById varmis review et
	
	Brand getByBrandName(String brandName);
}

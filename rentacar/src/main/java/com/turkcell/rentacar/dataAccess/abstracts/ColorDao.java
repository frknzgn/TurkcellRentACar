package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.entites.Color;

@Repository
public interface ColorDao extends JpaRepository<Color, Integer> {
	boolean existsByColorName(String name);
}

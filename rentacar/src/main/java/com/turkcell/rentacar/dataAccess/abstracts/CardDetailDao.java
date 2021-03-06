package com.turkcell.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.turkcell.rentacar.entites.concretes.CardDetail;

@Repository
public interface CardDetailDao extends JpaRepository<CardDetail, Integer>{
	
	boolean existsByCardNo(String cardNo);
	
}

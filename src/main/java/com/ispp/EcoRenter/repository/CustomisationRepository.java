package com.ispp.EcoRenter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ispp.EcoRenter.model.Customisation;

@Repository
public interface CustomisationRepository extends JpaRepository<Customisation, Integer> {

	@Query("select c from Customisation c")
	Customisation[] find();
	
}

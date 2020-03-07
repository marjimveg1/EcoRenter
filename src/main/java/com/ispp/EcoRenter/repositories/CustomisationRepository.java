package com.ispp.EcoRenter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ispp.EcoRenter.model.Customisation;

@Repository
public interface CustomisationRepository extends JpaRepository<Customisation, Integer> {

	@Query(value = "select c from Customisation c where c.id=?0")
	Customisation find(int customisationId);
	
}

package com.ispp.EcoRenter.repository;

import java.util.Collection;

import com.ispp.EcoRenter.model.Smallholding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SmallholdingRepository extends JpaRepository<Smallholding,Integer> {

    @Query("select sh from Smallholding sh where sh.owner.id = ?1")
    Collection<Smallholding> findSmallholdingsByOwnerId(int ownerId);

    @Query("select sh from Smallholding sh where sh.isAvailable=true")
    Collection<Smallholding> findSmallholdingsAvailables();
}
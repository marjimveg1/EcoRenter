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

    @Query("select case when count(ro) > 0 then false else true end from RentOut ro where ro.renter.id = ?1 and ro.smallholding.id = ?2")
    Boolean isSmallholdingRentedByRenter(int renterId, int smallholdingId);
}
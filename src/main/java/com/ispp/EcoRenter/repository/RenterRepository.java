package com.ispp.EcoRenter.repository;

import com.ispp.EcoRenter.model.Renter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RenterRepository extends JpaRepository<Renter,Integer> {

    @Query("select r from Renter r where r.userAccount.username = ?1")
    Renter findRenterByUsername(String username);
}
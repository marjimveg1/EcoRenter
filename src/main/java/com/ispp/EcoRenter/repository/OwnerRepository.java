package com.ispp.EcoRenter.repository;

import com.ispp.EcoRenter.model.Owner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {

    @Query("select o from Owner o where o.userAccount.id = ?1")
    Owner findOwnerByUserAccountId(int userAccountId);
}